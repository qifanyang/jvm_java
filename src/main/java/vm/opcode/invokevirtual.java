package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.RTObject;
import vm.runtime.StackFrame;
import vm.util.DescriptorUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * 调用对象所属类中的方法(包括从父类中继承的),操作数为常量池索引,对应的数据结构为MethodRef,因为当前ClassFile中methodInfo[]没有包含
 * 父类的methodInfo,所以invokevirtual会有一个向上递归查找的过程,(虚方法表可以用来优化)
 * 先将引用压入操作数栈,再压入参数, 然后执行方法调用, 在执行该指令的时候,操作数栈中参数已经准备好了,注意方法调用参数顺序
 *
 * 当父类引用指向子类对象,调用方法的方法引用是指向父类的, invokevirtual #4                  // Method source/Father.say:()V
 * 不能根据操作数指向的methodref查找方法,应该根据当前objectref去查找对应的class
 * 为什么对象是son可以编译器却要使用father的方法签名?
 * 这就是动态绑定,在运行时根据对象的class去绑定实际要执行的字节码
 * 对应的invokespecial叫静态绑定,在加载类阶段就可以确定要调用的方法直接引用
 *
 * @author yangqf
 * @version 1.0 2016/4/3
 */
public class invokevirtual extends OpcodeSupport{
    @Override
    public int opcode(){
        return 182;
    }


    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);

        //动态绑定,要根据对象的reference来确定RTClass
        //从当前栈帧中取出参数
        LinkedList<Object> paraList = new LinkedList<>();
        while(!frame.getOperands().isEmpty()){
            paraList.addFirst(frame.getOperands().pop());
        }
        Object[] paraObjectsForCall = paraList.toArray(new Object[paraList.size()]);

        RTObject refrence = (RTObject) paraList.removeFirst();//remove objectref, 反射调用不需要this
        Object[] paraObjectsForReflectCall = paraList.toArray(new Object[paraList.size()]);


        //invokevirtual 要调用的目标方法
        ConstantMethodRefInfo methodRefInfo = indexConstantPoolObject(frame, operand, ConstantMethodRefInfo.class);
        //方法所属的类,如果没有加载要先加载,对象都创建类一定加载了
        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        //方法调用所在的rtclass
//        RTClass rtClass = RTMethodArea.loadClass(classNameUtf8Info.string());
        RTClass rtClass = refrence.getRtClass();

        //准备构建方法名和方法描述符
        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        String methodName = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class).string();
        String methodDescriptor = indexConstantPoolObject(frame, nameAndTypeInfo.getDescriptor_index(), ConstantUtf8Info.class).string();

        //methodRefInfo是当前常量池中的methodref,需要根据该值到rtClass中去查找目标methodinfo
        MethodInfo methodInfo = rtClass.searchRecursiveMethodInfo(methodName, methodDescriptor);

        int accessFlag = methodInfo.getAccess_flags().value;
        if((accessFlag & 0x0100) != 0){
            //被调用的目标方法为本地方法,使用发射模拟本地方法调用
            String targetMethodClass = classNameUtf8Info.string();
            String targetMethodClassNative = targetMethodClass + "Native";
            String replace = targetMethodClassNative.replace("/",".");
            try{
                Class<?> targetNativeCalss = Class.forName(replace);
                //根据descriptor转换为对应class
                Class<?>[] classes = DescriptorUtil.fromDescriptor(methodDescriptor);
                Method targetMethod = targetNativeCalss.getDeclaredMethod(methodName, classes);
                //调用方法之前已经把objectref和parameter压栈, 这里需要把所有参数都取出来
//                LinkedList<Object> paraList = new LinkedList<>();
//                while(!frame.getOperands().isEmpty()){
//                    paraList.addFirst(frame.getOperands().pop());
//                }
//                paraList.removeFirst();//remove objectref
//                Object[] paraObjects = paraList.toArray(new Object[paraList.size()]);
                targetMethod.invoke(targetNativeCalss, paraObjectsForReflectCall);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }catch(NoSuchMethodException e){
                e.printStackTrace();
            }catch(InvocationTargetException e){
                e.printStackTrace();
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }else {
            if((accessFlag & 0x0020) != 0){//同步方法,需要自动执行monitor enter
                refrence.monitorEnter();
            }
            //methodInfo可能是从父类继承的, 所以RTClass也改变了
            StackFrame newFrame = frame.getThreadStack().createStackFrame(methodInfo);
            //方法调用,填充参数到新栈帧
            //...objectref,x,y->
            for(int i = 0; i < newFrame.getLocals().length; i++){
                newFrame.getLocals()[i] = paraObjectsForCall[i];
            }

        }
        return null;
    }
}
