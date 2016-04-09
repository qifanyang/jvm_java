package vm.opcode;

import vm.runtime.RTObject;
import vm.runtime.StackFrame;
import vm.util.Pair;

import java.util.Set;

/**
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class monitorexit extends OpcodeSupport{
    @Override
    public int opcode(){
        return 195;
    }

    @Override
    public Object operate(StackFrame frame){

        RTObject rtObject = (RTObject) frame.getOperands().pop();
        rtObject.monitorExit();
//        Pair<Thread, Integer> monitor = rtObject.getMonitor();
//        if(monitor.getFirst() != Thread.currentThread()){
//            throw new IllegalStateException("不是当前monitor拥有者, 不能执行monitorexit");
//        }
//
//        Integer second = monitor.getSecond();
//        if(second == 1){
//            monitor.setFirst(null);
//            monitor.setSecond(null);
//            //当前持有monitor的线程完成同步代码执行,退出同步,怎唤醒等待的线程
//            Set<Thread> waitSet = rtObject.getWaitSet();
//            if(!waitSet.isEmpty()){
//                //默认唤起第一个,但是jvm规范不保证唤醒哪一个线程
//                Thread thread = waitSet.toArray(new Thread[waitSet.size()])[0];
//                thread.notify();
//            }
//        }else {
//            monitor.setSecond(second - 1);
//        }
        return null;
    }
}
