package vm.opcode;

import vm.runtime.RTObject;
import vm.runtime.StackFrame;

/**
 * 将本地变量数组指定的索引位置的reference压入操作数栈, 操作数为unsigned byte
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class aload extends OpcodeSupport{
    @Override
    public int opcode(){
        return 25;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 1);
        frame.getOperands().push((RTObject)frame.getLocals()[operand]);
        return null;
    }
}
