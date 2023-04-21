package vm.runtime;

import vm.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 运行时对象, 通过new指令创建, 放在{@link RTHeap}中
 *
 * @author yangqf
 * @version 1.0 2016/4/4
 */
@lombok.Setter
@lombok.Getter
public class RTObject {
    /**
     * 对象的类数据,实现类型指针
     * 基于句柄的对象访问方式,不包含执行类的指针
     */
    private RTClass rtClass;
    //实例变量,包括父类的私有实例变量,jls12.5
    private Map<String, Object> insVariableMap = new HashMap<>();
    /**
     * jls17.2
     * Wait sets are manipulated solely through the methods Thread.interrupt Object.wait, Object.notify, and Object.notifyAll
     * <p>
     * Object.wait(), 当前线程被添加到waitSet中,在使用java coding时 ,wait方法应当在循环中使用,
     * Object.notify() 某一线程被唤醒,并从waitSet中移除,该线程在持有monitor线程执行monitorexit之后得以执行
     * Object.notifyAll(), 所有线程从waitSet中移除,但是只有一个可以锁住monitor
     * Thread.interrupt(), 调用者从waitSet中移除,线程中断状态被设置
     */
    private Set<Thread> waitSet = new CopyOnWriteArraySet<>();
    /**
     * jls17.1
     * 每个对象都与一个monitor关联,同一时间只有一个thread能够持有该monitor
     * Thread.sleep(), 暂停当前线程执行,但是不是去monitor
     */
    private Pair<Thread, Integer> monitor = new Pair<>();


    /**
     * 执行monitorenter
     */
    public void monitorEnter() {
        if (monitor.getFirst() == null) {
            //同步对象的monitor没有被其它线程获取
            monitor.setFirst(Thread.currentThread());
            monitor.setSecond(1);
        } else if (monitor.getFirst() == Thread.currentThread()) {
            //已经被自己获取,重入entry count增加
            monitor.setSecond(monitor.getSecond() + 1);
        } else {
            //被其它线程获取了
            this.waitSet.add(Thread.currentThread());
            try {
                //没有获取到monitor,线程挂起
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                this.waitSet.remove(Thread.currentThread());
                e.printStackTrace();
            }
        }

    }

    public void monitorExit() {
        if (monitor.getFirst() == null) return;
        if (monitor.getFirst() != Thread.currentThread()) {
            throw new IllegalStateException("不是当前monitor拥有者, 不能执行monitorexit");
        }

        Integer second = monitor.getSecond();
        if (second == 1) {
            monitor.setFirst(null);
            monitor.setSecond(null);
            //当前持有monitor的线程完成同步代码执行,退出同步,怎唤醒等待的线程
            if (!waitSet.isEmpty()) {
                //默认唤起第一个,但是jvm规范不保证唤醒哪一个线程
                Thread thread = waitSet.toArray(new Thread[waitSet.size()])[0];
                thread.notify();
            }
        } else {
            monitor.setSecond(second - 1);
        }
    }

}
