package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iadd extends OpcodeSupport {
    @Override
    public int opcode() {
        return 96;
    }

    @Override
    public String toString() {
        return "iadd";
    }

    @Override
    public Object operate(StackFrame frame) {
        Object a = frame.getOperands().pop();
        Object b = frame.getOperands().pop();
        Integer ia = (Integer) a;
        Integer ib = (Integer) b;
        Integer iadd = ia + ib;
        frame.getOperands().push(iadd);
        return null;
    }
}
