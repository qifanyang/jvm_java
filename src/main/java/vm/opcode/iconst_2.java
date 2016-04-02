package vm.opcode;

import jdk.internal.org.objectweb.asm.Opcodes;
import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iconst_2 implements Opcode{
    @Override
    public int opcode(){
        return Opcodes.ICONST_2;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getOperands()[0]=2;
        return null;
    }
}
