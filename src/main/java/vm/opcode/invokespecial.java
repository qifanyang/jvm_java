package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class invokespecial extends OpcodeSupport{
    @Override
    public int opcode(){
        return 183;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);
        //init
        frame.getOperands().pop();
        return null;
    }
}
