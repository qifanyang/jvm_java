package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.ConstantClassInfo;
import vm.parser.cp.ConstantMethodRefInfo;
import vm.parser.cp.ConstantNameAndTypeInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.StackFrame;
import vm.util.DescriptorUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class invokestatic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 184;//b8
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);

        ConstantMethodRefInfo methodRefInfo = indexConstantPoolObject(frame, operand, ConstantMethodRefInfo.class);
        //方法所属的类,如果没有加载要先加载,对象都创建类一定加载了
        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        //方法调用所在的rtclass
        RTClass rtClass = RTMethodArea.loadClass(classNameUtf8Info.string());

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
                LinkedList<Object> paraList = new LinkedList<>();
                while(!frame.getOperands().isEmpty()){
                    paraList.addFirst(frame.getOperands().pop());
                }
//                paraList.removeFirst();//remove objectref
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

            StackFrame newFrame = frame.getThreadStack().createStackFrame(methodInfo);
            //方法调用,填充参数到新栈帧
            //...objectref,x,y->
            for(int i = newFrame.getLocals().length - 1; i >= 0; i--){
                newFrame.getLocals()[i] = frame.getOperands().pop();
            }

        }
        return null;
    }
}
