package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.StackFrame;

/**
 * 调用实例方法,操作数为MethodRef,方法调用过程:<br>
 * 先将引用压入操作数栈,再压入参数, 然后执行方法调用
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

        //使用indexConstantPoolObject方法更简洁,更方便
        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        String className = frame.getConstantPool()[constantClassInfo.getName_index().value].toString();
        //目标方法所有类的运行时直接引用
        RTClass rtClass = RTMethodArea.findClass(className);
        if(null == rtClass){
            rtClass = RTMethodArea.loadClass(className);
        }
        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);

        ConstantPoolInfo[] constant_pool_info = rtClass.getClassFile().getConstant_pool_info();
        MethodInfo methodInfo = rtClass.methodInfoByMethodref(methodRefInfo);
        frame.getThreadStack().createStackFrame(methodInfo, rtClass.getClassFile().getConstant_pool_info());
        return null;
    }
}
