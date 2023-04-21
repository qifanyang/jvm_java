package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class astore_0 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 75;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getLocals()[0] = frame.getOperands().pop();
        return null;
    }
}
