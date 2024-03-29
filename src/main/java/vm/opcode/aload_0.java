package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 引用压栈
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class aload_0 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 42;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getLocals()[0]);
        return null;
    }
}
