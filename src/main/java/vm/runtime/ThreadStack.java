package vm.runtime;

import java.util.LinkedList;
import java.util.List;

/**
 * 线程栈,创建线程时创建,线程结束时销毁,存储方法调用栈帧
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Data
public class ThreadStack {
    private int pc;
    private final static int MAX_STACK_FRAME_DEEP = 1000;
    private LinkedList<StackFrame> frames = new LinkedList<>();

    /**
     * 方法调用,创建栈帧并压栈
     */
    public void createStackFrame(){
        if(frames.size() > MAX_STACK_FRAME_DEEP){
            throw new StackOverflowError("方法调用嵌套太多");
        }

        StackFrame frame = new StackFrame();
        //TODO 初始化

        frames.addLast(frame);//压栈
    }

    /**
     * 调用方法结束,从线程栈中移除栈帧
     */
    public void popStackFrame(){
        frames.removeLast();
    }


}
