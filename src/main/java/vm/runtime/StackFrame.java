package vm.runtime;

import vm.opcode.Opcode;
import vm.parser.AttributeInfo;
import vm.parser.MethodInfo;
import vm.parser.U1;
import vm.parser.attribute.CodeAttribute;
import vm.parser.cp.ConstantPoolInfo;

import java.util.Iterator;

/**
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Getter
@lombok.Setter
public class StackFrame {
    private Object locals[];//用于方法形参和局部变量存储
    private OperandStack operands;

    private U1 code[];//被调用方法的字节码

    private ConstantPoolInfo[] constantPool;//指向常量池的引用,用于实现动态链接,将符号引用解析为实际引用
    private MethodInfo methodInfo;
    private ThreadStack threadStack;
    private int pc;//方法调用方法返回时继续执行pc指向的字节码指令
    private Opcode currentOpcode;
    private int jumpOffset;//跳转偏移量,当执行了跳转指令时该值被设定为偏移量,没有跳转的话值为0

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
        System.out.println("pc = " + pc);
        System.out.println("opcode = " + currentOpcode);
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

    public void setPc(int pc){
        this.pc = pc;
        this.threadStack.setPc(pc);
    }

    /**
     * 跳转指令会修改该值,字节码执行单元不会按1+操作数个数增加pc值
     * @param offset
     */
    public void jumpOffset(int offset){
        this.jumpOffset = offset;
    }

}
