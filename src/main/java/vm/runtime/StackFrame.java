package vm.runtime;

import vm.parser.AttributeInfo;
import vm.parser.MethodInfo;
import vm.parser.U1;
import vm.parser.attribute.CodeAttribute;
import vm.parser.cp.ConstantPoolInfo;

import java.util.Iterator;

/**
 * 栈帧,方法调用时创建, 用于存储局部变量, 操作数栈,...
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Getter
public class StackFrame {
    private Object locals[];
    private OperandStack operands;

    private U1 code[];

    private ConstantPoolInfo[] constantPool;//用于解析符号引用
    private MethodInfo methodInfo;
    private ThreadStack threadStack;

    public void init(MethodInfo methodInfo, ConstantPoolInfo[] constantPool, ThreadStack threadStack){
        this.constantPool = constantPool;
        this.methodInfo = methodInfo;
        this.threadStack = threadStack;

        AttributeInfo[] attributes = methodInfo.getAttributes();
        CodeAttribute codeAttributes = (CodeAttribute) attributes[0].getAttributes().get(0);

        locals = new Object[codeAttributes.getMax_locals().value];
        operands = new OperandStack(codeAttributes.getMax_stack().value);
        code = codeAttributes.getCode();
    }

    public void show(){
        System.out.println("-------StackFrame-----------");
        System.out.println("local variable :");
        for(Object o : locals){
            System.out.println(o);
        }
        System.out.println("operands :");
        for(Iterator<Object> iterator = operands.iterator(); iterator.hasNext();){
            Object next = iterator.next();
            System.out.println(next);
        }
        System.out.println("----------------------------");
    }
}
