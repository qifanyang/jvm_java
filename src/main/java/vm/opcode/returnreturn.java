package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/4
 */
public class returnreturn extends OpcodeSupport{
    @Override
    public int opcode(){
        return 177;
    }

    @Override
    public Object operate(StackFrame frame){
        frame.getThreadStack().popStackFrame();
        //没有处理返回值
        return null;
    }
}
