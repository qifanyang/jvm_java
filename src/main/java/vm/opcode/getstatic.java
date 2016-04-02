package vm.opcode;

import vm.runtime.StackFrame;

/**
 * Operation: Get static field from class
 * Format: getstatic indexbyte1 indexbyte2  , (indexbyte1 << 8) | indexbyte2)
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class getstatic extends OpcodeSupport{
    @Override
    public int opcode(){
        return 178;
    }

    @Override
    public Object operate(StackFrame frame){

        return null;
    }
}
