package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public interface Opcode{

    public int opcode();

    /**
     * 返回该字节码指令的操作数个数,大多数指令的操作数个数为0
     * @return
     */
//    public int operandNum();

    public void register();

    public Object operate(StackFrame frame);
}
