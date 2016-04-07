package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 调用接口方法,
 * invokeinterface
 * indexbyte1
 * indexbyte2
 * count
 * 0
 *
 * indexbyte1和indexbyte2用于构建常量池索引
 *
 * 方法搜索过程:
 * 假如C是objectref指向的class,引用指向的对象包含类的Class的直接引用
 * 1.假如c包含名字和描述符都相同的实例方法,搜索终止,调用该方法
 * 2.递归查找C的直接父类,重复步骤1
 * 3.没有找到匹配的实例方法,抛出AbstractMethodError
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class invokeinterface extends OpcodeSupport{
    @Override
    public int opcode(){
        return 185;
    }

    @Override
    public Object operate(StackFrame frame){

        return null;
    }
}
