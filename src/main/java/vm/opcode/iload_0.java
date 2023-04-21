package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class iload_0 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 26;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getLocals()[0]);
        return null;
    }
}
