package vm.runtime;

import java.util.LinkedList;
import java.util.List;

/**
 * �߳�ջ,�����߳�ʱ����,�߳̽���ʱ����,�洢��������ջ֡
 * @author yangqf
 * @version 1.0 2016/4/1
 */
@lombok.Data
public class ThreadStack {
    private int pc;
    private final static int MAX_STACK_FRAME_DEEP = 1000;
    private LinkedList<StackFrame> frames = new LinkedList<>();

    /**
     * ��������,����ջ֡��ѹջ
     */
    public void createStackFrame(){
        if(frames.size() > MAX_STACK_FRAME_DEEP){
            throw new StackOverflowError("��������Ƕ��̫��");
        }

        StackFrame frame = new StackFrame();
        //TODO ��ʼ��

        frames.addLast(frame);//ѹջ
    }

    /**
     * ���÷�������,���߳�ջ���Ƴ�ջ֡
     */
    public void popStackFrame(){
        frames.removeLast();
    }


}
