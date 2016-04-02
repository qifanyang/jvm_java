package vm.runtime;

import vm.parser.AttributeInfo;
import vm.parser.MethodInfo;
import vm.parser.U1;
import vm.parser.attribute.CodeAttribute;
import vm.parser.cp.ConstantPoolInfo;

/**
 * 栈帧,方法调用时创建, 用于存储局部变量, 操作数栈,...
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Data
public class StackFrame {
    private Object locals[];
    private Object operands[];

    private U1 code[];

    private ConstantPoolInfo[] constantPool;//用于解析符号引用
    private MethodInfo methodInfo;
    public void init(MethodInfo methodInfo, ConstantPoolInfo[] constantPool){
        this.constantPool = constantPool;
        this.methodInfo = methodInfo;

        AttributeInfo[] attributes = methodInfo.getAttributes();
        CodeAttribute codeAttributes = (CodeAttribute) attributes[0].getAttributes().get(0);

        locals = new Object[codeAttributes.getMax_locals().value];
        operands = new Object[codeAttributes.getMax_stack().value];
        code = codeAttributes.getCode();
    }
}
