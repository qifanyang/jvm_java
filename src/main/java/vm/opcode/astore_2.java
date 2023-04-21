package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class astore_2 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 77;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getLocals()[2] = frame.getOperands().pop();
        return null;
    }
}
