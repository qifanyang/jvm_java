package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 将栈顶的值弹出放到调用该改方法的栈帧的操作数栈上
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class ireturn extends OpcodeSupport{
    @Override
    public int opcode(){
        return 172;
    }

    @Override
    public Object operate(StackFrame frame){
        Object returnValue = frame.getOperands().pop();
        StackFrame lastFrame = frame.getThreadStack().popStackFrame();
        lastFrame.getOperands().push(returnValue);
        return null;
    }
}
