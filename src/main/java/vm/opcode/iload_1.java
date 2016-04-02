package vm.opcode;

import vm.runtime.StackFrame;

/**
 * iload_0, iload index (index为unsigned byte)
 * 加载指定index local variable array到操作数栈上, 本地变量表中必须是整形
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
