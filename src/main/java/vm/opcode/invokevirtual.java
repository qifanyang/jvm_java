package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.StackFrame;
import vm.util.DescriptorUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * 调用实例方法,操作数为MethodRef,方法调用过程:<br>
 * 先将引用压入操作数栈,再压入参数, 然后执行方法调用
 *
 *
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

        //invokevirtual 要调用的目标方法
        ConstantMethodRefInfo methodRefInfo = indexConstantPoolObject(frame, operand, ConstantMethodRefInfo.class);
        //方法所属的类,如果没有加载要先加载,对象都创建类一定加载了
        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        //目标方法所有类的运行时直接引用
        RTClass rtClass = RTMethodArea.findClass(classNameUtf8Info.string());
        if(null == rtClass){
            rtClass = RTMethodArea.loadClass(classNameUtf8Info.string());
        }
        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        String methodName = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class).string();
        String methodDescriptor = indexConstantPoolObject(frame, nameAndTypeInfo.getDescriptor_index(), ConstantUtf8Info.class).string();
        ConstantPoolInfo[] constant_pool_info = rtClass.getClassFile().getConstant_pool_info();
        //methodRefInfo是当前常量池中的methodref,需要根据该值到rtClass中去查找目标methodinfo
        MethodInfo methodInfo = rtClass.getMethodInfoByMethodref(methodName, methodDescriptor);

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
                LinkedList<Object> paraList = new LinkedList<>();
                while(!frame.getOperands().isEmpty()){
                    paraList.add(frame.getOperands().pop());
                }
                paraList.removeLast();//objectref
                Object[] paraObjects = paraList.toArray(new Object[paraList.size()]);
                targetMethod.invoke(targetNativeCalss, paraObjects);
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
            //实例方法调用,暂时不处理构造方法,不要在构造方法里面加初始化
//            if(methodName.equals("<init>"))return null;

            frame.getThreadStack().createStackFrame(methodInfo, rtClass.getClassFile().getConstant_pool_info());
        }
        return null;
    }
}
