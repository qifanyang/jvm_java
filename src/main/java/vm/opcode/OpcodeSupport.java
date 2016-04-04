package vm.opcode;

import vm.parser.U1;
import vm.runtime.StackFrame;
import vm.runtime.ThreadStack;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public abstract class OpcodeSupport implements Opcode{

    @Override
    public void register(){
    }

    @Override
    public int operandNum(){
        return 0;
    }

    /**
     * 取出当前opcode的操作数,操作数个数根据方法{@link OpcodeSupport#operandNum()} 返回值决定
     * @param frame
     * @return
     */
    protected int fetchOperand(StackFrame frame){
        int pc = frame.getThreadStack().getPc();//goto pc实现跳转
        int operandNum = operandNum();
        int operand = 0;
        if(1 == operandNum){
            U1 b1 = frame.getCode()[pc];
            operand = b1.value;
            frame.getThreadStack().setPc(pc+1);
        }else if(2 == operandNum){
            U1 b1 = frame.getCode()[pc];
            U1 b2 = frame.getCode()[pc+1];
            operand = (((byte)b1.value) << 8)| ((byte)b2.value);
            frame.getThreadStack().setPc(pc+2);
        }else {
            throw new IllegalStateException("无法读取操作数, 该指令操作数个数:"+operandNum);
        }

        return operand;
    }

}
