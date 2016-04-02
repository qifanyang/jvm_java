package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class istore_2 implements Opcode{
    @Override
    public int opcode(){
        return 61;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getLocals()[2]=frame.getOperands()[0];
        frame.getOperands()[0]=null;
        return null;
    }
}
