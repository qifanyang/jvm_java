package vm.opcode;

import vm.runtime.StackFrame;

/**
 * iload_0, iload index (indexΪunsigned byte)
 * ����ָ��index local variable array��������ջ��, ���ر������б���������
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class iload_1 extends OpcodeSupport{
    @Override
    public int opcode(){
        return 27;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getOperands().push(frame.getLocals()[1]);
        return null;
    }
}
