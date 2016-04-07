package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class if_icmple extends OpcodeSupport{
    @Override
    public int opcode(){
        return 164;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);//比较成功的话,新的pc offset, 更新pc是加上offset的方式
        Integer value2 = (Integer) frame.getOperands().pop();
        Integer value1 = (Integer) frame.getOperands().pop();
        if(value1.intValue() <= value2.intValue()){
            frame.jumpOffset(operand);
        }
        return null;
    }
}
