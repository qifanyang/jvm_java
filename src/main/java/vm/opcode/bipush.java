package vm.opcode;

import vm.runtime.StackFrame;

/**
 * push byte, byte带符号扩展为int
 * <p>
 * 这里等同于iconst_n 如果n为-1,0,1,2,3,4,5
 *
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class bipush extends OpcodeSupport {
    @Override
    public int opcode() {
        return 16;
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 1);
        frame.getOperands().push(operand);
        return null;
    }
}
