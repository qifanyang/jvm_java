package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class aload_2 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 44;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getLocals()[2]);
        return null;
    }
}
