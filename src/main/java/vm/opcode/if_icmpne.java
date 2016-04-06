package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 条件判断,if_icmpne succeeds if and only if value1 ≠ value2, 当切仅当value1不等于value2 比较结果才会成功,
 * i表示在栈中比较的对象为int, 当成功时pc跳转到2字节操作数指向的地址,
 * @author yangqf
 * @version 1.0 2016/4/6
 */
public class if_icmpne extends OpcodeSupport{
    @Override
    public int opcode(){
        return 160;
    }

    @Override
    public Object operate(StackFrame frame){
        int operand = fetchOperand(frame, 2);//比较成功的话,新的pc offset
        Integer value1 = (Integer) frame.getOperands().pop();
        Integer value2 = (Integer) frame.getOperands().pop();
        if(!value1.equals(value2)){
            frame.setPc(operand);//比较成功,跳转
        }
        return null;
    }
}
