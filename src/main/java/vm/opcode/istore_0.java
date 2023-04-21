package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class istore_0 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 59;//0x3b
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getLocals()[0] = frame.getOperands().pop();
        return null;
    }
}
