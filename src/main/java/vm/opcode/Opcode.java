package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public interface Opcode{

    public int opcode();

    public void register();

    public Object operate(StackFrame frame);

    public Object operateAndIncPC(StackFrame stackFrame);
}