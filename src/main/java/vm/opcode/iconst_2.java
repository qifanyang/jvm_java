package vm.opcode;

import jdk.internal.org.objectweb.asm.Opcodes;
import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iconst_2 extends OpcodeSupport {
    @Override
    public int opcode() {
        return 5;
    }

    @Override
    public Object operate(StackFrame frame) {
        frame.getOperands().push(2);
        return null;
    }
}
