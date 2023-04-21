package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/6
 */
public class ifeq extends OpcodeSupport {
    @Override
    public int opcode() {
        return 153;//0x99
    }

    @Override
    public Object operate(StackFrame frame) {
        int offset = fetchOperand(frame, 2);
        Integer v = (Integer) frame.getOperands().pop();
        if (v.equals(0)) {
            frame.jumpOffset(offset);
        }
        return null;
    }
}
