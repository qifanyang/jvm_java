package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class istore extends OpcodeSupport {
    @Override
    public int opcode() {
        return 54;//0x36
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 1);
        frame.getLocals()[operand] = frame.getOperands().pop();
        return null;
    }
}
