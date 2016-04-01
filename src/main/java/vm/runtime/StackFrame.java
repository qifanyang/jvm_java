package vm.runtime;

import vm.parser.cp.ConstantPoolInfo;

/**
 * ջ֡,��������ʱ����, ���ڴ洢�ֲ�����, ������ջ,...
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Data
public class StackFrame {
    private Object locals[];
    private Object operands[];

    private ConstantPoolInfo constantPool;//���ڽ�����������
}
