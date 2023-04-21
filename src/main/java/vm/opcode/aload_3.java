package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class aload_3 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 45;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(frame.getLocals()[3]);
        return null;
    }
}
