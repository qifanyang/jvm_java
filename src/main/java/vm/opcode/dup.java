package vm.opcode;

import vm.runtime.StackFrame;

/**
 * Duplicate the top value on the operand stack and push the
 * duplicated value onto the operand stack
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
