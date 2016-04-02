package vm.opcode;

import jdk.internal.org.objectweb.asm.Opcodes;
import vm.runtime.StackFrame;

/**
 * istore_0 = 59 (0x3b)
 * istore_1 = 60 (0x3c)
 * istore_2 = 61 (0x3d)
 * istore_3 = 62 (0x3e)
 * ��������ջ���˵�ֵ����,���洢��nָ��ı��ر������� istore index, istore_1
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public class istore_1 extends OpcodeSupport{
    @Override
    public int opcode(){
        return 60;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getLocals()[1]=frame.getOperands().pop();
        return null;
    }
}
