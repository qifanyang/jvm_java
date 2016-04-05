package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class iconst_0 extends OpcodeSupport{
    @Override
    public int opcode(){
        return 3;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getOperands().push(0);
        return null;
    }
}
