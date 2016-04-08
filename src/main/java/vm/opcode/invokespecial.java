package vm.opcode;

import vm.parser.MethodInfo;
import vm.parser.cp.*;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.StackFrame;

/**
 * invokespecial与invokevirtual区别,后者用于调用对象所属类中的方法(包括从父类中继承的)
 * 改指令用于调用构造方法<init>,private方法(access_flag区分,不用递归查找),父类方法(access_flag区分,以及methodref中的class_name_index)
 *
 * 方法调用时操作数为常量池索引,指向的数据为ConstantMethodref,包含了方法名,描述符,类名, 进行方法调用前对象的直接引用被压栈了
 * 通过栈中的对象直接引用可以找到对应的RTClass,通过RTClass和methodref查找methodInfo,然后进行方法调用
 *
 *
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class invokespecial extends OpcodeSupport{
    @Override
    public int opcode(){
        return 183;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);
        //init  Method java/lang/Object."<init>":()V   字节码就一个return, 不做其它任何操作

        ConstantMethodRefInfo methodRefInfo = indexConstantPoolObject(frame, operand, ConstantMethodRefInfo.class);
        //方法所属的类,如果没有加载要先加载,对象都创建类一定加载了
        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, methodRefInfo.getClass_index(), ConstantClassInfo.class);

        ConstantUtf8Info classNameUtf8Info = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);
        //目标方法所有类的运行时直接引用
        RTClass rtClass = RTMethodArea.loadClass(classNameUtf8Info.string());

        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, methodRefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        String methodName = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class).string();
        String methodDescriptor = indexConstantPoolObject(frame, nameAndTypeInfo.getDescriptor_index(), ConstantUtf8Info.class).string();
        ConstantPoolInfo[] constant_pool_info = rtClass.getClassFile().getConstant_pool_info();
        //methodRefInfo是当前常量池中的methodref,需要根据该值到rtClass中去查找目标methodinfo
        MethodInfo methodInfo = rtClass.searchMethodInfo(methodName, methodDescriptor);

        //没有实现递归查找
        StackFrame newFrame = frame.getThreadStack().createStackFrame(methodInfo);
        //方法调用,填充参数到新栈帧
        //...objectref,x,y->
        for(int i = newFrame.getLocals().length - 1; i >= 0; i--){
            newFrame.getLocals()[i] = frame.getOperands().pop();
        }

       // System.out.println("invokespecical : " + classNameUtf8Info.string() + ":" + methodName);
        return null;
    }
}
