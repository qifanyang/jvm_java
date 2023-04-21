package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class dup extends OpcodeSupport {
    @Override
    public int opcode() {
        return 89;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getOperands().peek());
        return null;
    }
}
