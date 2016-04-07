package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class aload_1 extends OpcodeSupport{
    @Override
    public int opcode(){
        return 43;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getOperands().push(frame.getLocals()[1]);
        return null;
    }
}
