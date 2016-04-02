package vm.opcode;

import jdk.internal.org.objectweb.asm.Opcodes;
import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class istore_1 implements Opcode{
    @Override
    public int opcode(){
        return 60;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getLocals()[1]=frame.getOperands()[0];
        frame.getOperands()[0]=null;
        return null;
    }
}
