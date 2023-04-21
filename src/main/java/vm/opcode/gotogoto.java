package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class gotogoto extends OpcodeSupport {
    @Override
    public int opcode() {
        return 167;
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 2);

        //goto operand 值可能为负值
        frame.jumpOffset(operand);
        return null;
    }
}
