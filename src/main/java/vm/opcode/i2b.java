package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class i2b extends OpcodeSupport{
    @Override
    public int opcode(){
        return 145;
    }

    @Override
    public Object operate(StackFrame frame){
        Integer pop = (Integer) frame.getOperands().pop();
        frame.getOperands().push(Byte.valueOf((byte) pop.intValue()));
        return null;
    }
}
