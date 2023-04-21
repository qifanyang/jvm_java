package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class istore_3 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 62;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getLocals()[3] = frame.getOperands().pop();
        return null;
    }
}
