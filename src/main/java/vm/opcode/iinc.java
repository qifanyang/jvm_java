package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 根据index和const增加本地变量值
 * iinc
 *index
 *const
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class iinc extends OpcodeSupport{
    @Override
    public int opcode(){
        return 132;//0x84
    }

    @Override
    public Object operate(StackFrame frame){
        int index = fetchOperand(frame, 1);
        int cons = fetchOperand(frame, 1);

        Integer v = (Integer) frame.getLocals()[index];
        v = v.intValue() + cons;
        frame.getLocals()[index] = v;

        return null;
    }
}
