package vm.runtime;

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
    private int pc;//进入方法栈时重置pc为0, jvm说在线程栈中, 方法调用放在StackFrame更合适, 暂时还没想到放在这里的理由
    private final static int MAX_STACK_FRAME_DEEP = 50;
    private LinkedList<StackFrame> frames = new LinkedList<>();

    private StackFrame currentFrame;
    /**
     * 方法调用,创建栈帧并压栈
     */
    public StackFrame createStackFrame(MethodInfo methodInfo){
        if(frames.size() > MAX_STACK_FRAME_DEEP){
            throw new StackOverflowError("方法调用嵌套太多, size = " + frames.size());
        }

        StackFrame frame = new StackFrame();
        frame.init(methodInfo, methodInfo.getCf().getConstant_pool_info(), this);

        frames.addLast(frame);//压栈
        currentFrame = frame;
        return frame;
    }

    /**
     * 调用方法结束,从线程栈中移除栈帧
     */
    public StackFrame popStackFrame(){
        frames.removeLast();
        if(frames.isEmpty()){
            return null;
        }
        currentFrame = frames.getLast();
//        pc = currentFrame.getPc();
        return currentFrame;
    }


    @Override
    public void run(){
        while(true){
            try{
                OpcodeExecuteUnit.execute(currentFrame);
            }catch(Exception e){
                currentFrame.show();
                throw e;
            }

            if(frames.isEmpty()){
                System.out.println();
                System.out.println("虚拟机执行字节码完毕,正常退出...");
                break;
            }
            //方法调用完成,当前栈帧弹出,上一个栈帧成为新的当前栈帧
//            currentFrame = frames.pop();
        }
        System.out.println("线程栈退出...");
    }
}
