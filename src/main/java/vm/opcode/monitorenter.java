package vm.opcode;

import vm.runtime.RTObject;
import vm.runtime.StackFrame;
import vm.util.Pair;

/**
 * 同步指令,操作数栈中的objectref必须是引用类型.
 * 每个对象都与一个monitor关联,一个线程执行monitorenter尝试获取与对象关联的monitor的拥有权
 * 如果一个线程成功获取了一个monitor,monitor's entry count次数变为1, 如果线程重复获取同一对象monitor(reenter),
 * 没重入一次计数加1,如果另外一个线程已经拥有了该monitor,则当前线程被添加到monitor的wait set中,
 * 当拥有monitor的线程执行monitorexit , 知道monitor entry count 为0, wait set中的线程尝试获取
 * 该monitor
 * <p>
 * java提供了多种机制来实现线程间通讯,最基本的是使用synchronization method
 * 使用同步方法时,字节码中不会出现monitorenter指令, 因为虚拟机可以根据方法的access_flag自动执行monitorenter
 * 方法执行完毕时自动执行monitorexit, 要想看到字节码中的monitor指令使用synchronization(this){}
 *
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class monitorenter extends OpcodeSupport {
    @Override
    public int opcode() {
        return 194;//0xc2
    }

    @Override
    public Object operate(StackFrame frame) {
        //进入同步方法之前,先执行monitorenter获取monitor
        RTObject rtObject = (RTObject) frame.getOperands().pop();
        //因为invokevirtual 调用同步方法需要自动调用,所以放到RTObject中实现
        rtObject.monitorEnter();
//        //每个对象都关联一个monitor
//        Pair<Thread, Integer> monitor = rtObject.getMonitor();
//        if(monitor.getFirst() == null){
//            //同步对象的monitor没有被其它线程获取
//            monitor.setFirst(Thread.currentThread());
//            monitor.setSecond(1);
//        }else if(monitor.getFirst() == Thread.currentThread()){
//            //已经被自己获取,重入entry count增加
//            monitor.setSecond(monitor.getSecond()+1);
//        }else{
//            //被其它线程获取了
//            rtObject.getWaitSet().add(Thread.currentThread());
//            try{
//                //没有获取到monitor,线程挂起
//                Thread.currentThread().wait();
//            }catch(InterruptedException e){
//                rtObject.getWaitSet().remove(Thread.currentThread());
//                e.printStackTrace();
//            }
//        }
        return null;
    }
}
