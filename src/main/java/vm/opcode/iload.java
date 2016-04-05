package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class iload extends OpcodeSupport{
    @Override
    public int opcode(){
        return 21;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 1);
        frame.getOperands().push(frame.getLocals()[operand]);
        return null;
    }
}
