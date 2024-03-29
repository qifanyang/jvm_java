package vm.opcode;


import vm.runtime.StackFrame;

/**
 * iconst_n
 * 将常数n压入操作数栈, 等同于bipush i
 *
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iconst_1 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 4;
    }

    @Override
    public String toString() {
        return "iconst_1";
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(1);
        return null;
    }
}
