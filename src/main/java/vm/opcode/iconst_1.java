package vm.opcode;

import jdk.internal.org.objectweb.asm.Opcodes;
import vm.runtime.StackFrame;

/**
 * iconst_n
 * 将常数n压入操作数栈, 等同于bipush i
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iconst_1 extends OpcodeSupport{
    @Override
    public int opcode(){
        return Opcodes.ICONST_1;
    }

    @Override
    public String toString(){
        return "iconst_1";
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getOperands().push(1);
        return null;
    }
}
