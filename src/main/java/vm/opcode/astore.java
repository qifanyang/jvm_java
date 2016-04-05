package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 存储栈顶元素(是returnAddress或者reference)到本地变量数组, 本地变量数组索引为unsigned byte index,
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class astore extends OpcodeSupport{
    @Override
    public int opcode(){
        return 58;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 1);
        frame.getLocals()[operand] = frame.getOperands().pop();
        return null;
    }
}
