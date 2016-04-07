package vm.opcode;

import vm.runtime.StackFrame;

/**
 * int compare greater than
 *
 * ..., value1, value2 →
 * ...
 * if_icmpgt succeeds if and only if value1 > value2
 * 注意值得位置
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class if_icmpgt extends OpcodeSupport{
    @Override
    public int opcode(){
        return 163;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);//比较成功的话,新的pc offset, 更新pc是加上offset的方式
        Integer value2 = (Integer) frame.getOperands().pop();
        Integer value1 = (Integer) frame.getOperands().pop();
        if(value1.intValue() > value2.intValue()){
            frame.jumpOffset(operand);
        }
        return null;
    }
}
