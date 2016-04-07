package vm.opcode;

import vm.parser.ConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;
import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public abstract class OpcodeSupport implements Opcode{

    int operandLength;//最好放到frame中去

    public int getOperandLength(){
        int tmp = operandLength;
        this.operandLength = 0;
        return tmp;
    }

    @Override
    public void register(){
    }

//    @Override
    public int operandNum(){
        return 0;
    }

    /**
     * 取出当前opcode的操作数
     * @param frame
     * @param operandNum 操作数字节个数
     * @return
     */
    protected int fetchOperand(StackFrame frame, int operandNum){
        int pc = frame.getPc();//goto pc实现跳转
//        int operandNum = operandNum();
        int operand = 0;
        if(1 == operandNum){
            this.operandLength += 1;//连续两次读取一个字节的操作数
            U1 b1 = frame.getCode()[pc+this.operandLength];
            operand = b1.value;
//            frame.setPc(pc+1);
        }else if(2 == operandNum){
            U1 b1 = frame.getCode()[pc+1];
            U1 b2 = frame.getCode()[pc+2];
            operand = (((byte)b1.value) << 8)| ((byte)b2.value);
//            frame.setPc(pc+2);
            this.operandLength += 2;
        }else {
            throw new IllegalStateException("无法读取操作数, 该指令操作数个数:"+operandNum);
        }

        return operand;
    }

    protected <T> T indexConstantPoolObject(StackFrame frame, int index, Class<T> t){
        ConstantPoolObject constantPoolObject = frame.getConstantPool()[index].getConstantPoolObject();
        return (T) constantPoolObject;
    }

    protected <T> T indexConstantPoolObject(StackFrame frame, U2 index, Class<T> t){
        return indexConstantPoolObject(frame, index.value, t);
    }

}
