package vm.opcode;

import vm.parser.U1;
import vm.runtime.StackFrame;

/**
 * 调用实例方法,方法调用过程:<br>
 *
 *
 * @author yangqf
 * @version 1.0 2016/4/3
 */
public class invokevirtual extends OpcodeSupport{
    @Override
    public int opcode(){
        return 182;
    }

    @Override
    public int operandNum(){
        return 2;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame);


        return null;
    }
}
