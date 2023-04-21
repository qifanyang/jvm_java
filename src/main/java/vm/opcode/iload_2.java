package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iload_2 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 28;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getLocals()[2]);
        return null;
    }
}
