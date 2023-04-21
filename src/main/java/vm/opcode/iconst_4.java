package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class iconst_4 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 7;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(4);
        return null;
    }
}
