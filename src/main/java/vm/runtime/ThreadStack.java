package vm.runtime;

import vm.opcode.OpcodeExecuteUnit;
import vm.parser.MethodInfo;
import vm.parser.cp.ConstantPoolInfo;

import java.util.LinkedList;

/**
 * 线程栈,创建线程时创建,线程结束时销毁,存储方法调用栈帧
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Getter
@lombok.Setter
public class ThreadStack extends Thread{
    private int pc;//进入方法栈时重置pc为0
    private final static int MAX_STACK_FRAME_DEEP = 1000;
    private LinkedList<StackFrame> frames = new LinkedList<>();

    private StackFrame currentFrame;
    /**
     * 方法调用,创建栈帧并压栈
     */
    public void createStackFrame(MethodInfo methodInfo, ConstantPoolInfo[] constantPool){
        if(frames.size() > MAX_STACK_FRAME_DEEP){
            throw new StackOverflowError("方法调用嵌套太多");
        }

        StackFrame frame = new StackFrame();
        frame.init(methodInfo, constantPool, this);

        frames.addLast(frame);//压栈
        if(null != currentFrame){
            currentFrame.setPc(pc);
        }
        currentFrame = frame;

        System.out.println("方法调用");
    }

    /**
     * 调用方法结束,从线程栈中移除栈帧
     */
    public void popStackFrame(){
        currentFrame = frames.removeLast();
        pc = currentFrame.getPc();
    }


    @Override
    public void run(){
        while(true){
                try{
                    Thread.sleep(1000L);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            OpcodeExecuteUnit.execute(currentFrame);

            //方法调用完成,当前栈帧弹出,上一个栈帧成为新的当前栈帧
            currentFrame = frames.pop();
        }
    }
}
