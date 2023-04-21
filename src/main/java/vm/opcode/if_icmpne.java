package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 条件判断,if_icmpne succeeds if and only if value1 ≠ value2, 当切仅当value1不等于value2 比较结果才会成功,
 * i表示在栈中比较的对象为int, 当成功时pc跳转到2字节操作数指向的地址,
 *
 * @author yangqf
 * @version 1.0 2016/4/6
 */
public class if_icmpne extends OpcodeSupport {
    @Override
    public int opcode() {
        return 160;
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 2);//比较成功的话,新的pc offset, 更新pc是加上offset的方式
        Integer value2 = (Integer) frame.getOperands().pop();
        Integer value1 = (Integer) frame.getOperands().pop();
        if (!value1.equals(value2)) {
            //frame.setPc(frame.getPc()+operand-1-2);//比较成功,跳转, 使用偏移量offset的方式
            //在执行代码期间,pc值指向当前指令字节码位置, 因为跳转是根据当前pc位置+offset来定位将要跳转的位置
            frame.jumpOffset(operand);
        }
        return null;
    }
}
