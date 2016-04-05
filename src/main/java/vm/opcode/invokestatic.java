package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class invokestatic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 184;//b8
    }

    @Override
    public Object operate(StackFrame frame){
        return null;
    }
}
