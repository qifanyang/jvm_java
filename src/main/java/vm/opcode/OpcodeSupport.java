package vm.opcode;

import vm.runtime.StackFrame;
import vm.runtime.ThreadStack;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public abstract class OpcodeSupport implements Opcode{

    @Override
    public void register(){
//        OpcodeExecuteUnit.register(this);
    }

    @Override
    public Object operateAndIncPC(StackFrame stackFrame){
        pc1(stackFrame);
        return operate(stackFrame);
    }

    public void pc1(StackFrame stackFrame){
        pcn(stackFrame, 1);
    }
    public void pc2(StackFrame stackFrame){
        pcn(stackFrame, 2);
    }
    public void pc3(StackFrame stackFrame){
        pcn(stackFrame, 3);
    }
    public void pc4(StackFrame stackFrame){
        pcn(stackFrame, 4);
    }
    public void pcn(StackFrame stackFrame, int n){
        ThreadStack threadStack = stackFrame.getThreadStack();
        threadStack.setPc(threadStack.getPc()+n);
    }
}
