package vm.runtime;

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

    private ConstantPoolInfo constantPool;//用于解析符号引用
}
