package vm.opcode;

import vm.runtime.StackFrame;

/**
 * invokespecial与invokevirtual区别,后者用于调用对象所属类中的方法(包括从父类中继承的)
 * 改指令用于调用构造方法<init>,private方法(access_flag区分),父类方法(access_flag区分)
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
        //init
        frame.getOperands().pop();
        return null;
    }
}
