package vm.opcode;

import vm.runtime.StackFrame;
import vm.runtime.ThreadStack;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public abstract class OpcodeSupport implements Opcode{

    @Override
    public void register(){
//        OpcodeExecuteUnit.register(this);
    }

}
