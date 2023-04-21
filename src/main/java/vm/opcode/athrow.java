package vm.opcode;

import vm.runtime.StackFrame;

/**
 * Throw exception or error
 * 操作数栈中引用指向的对象的类必须是Throwable或者其子类.然后搜索当前方法的异常处理器,
 * 如果搜索到对应的异常处理器,它会包含处理异常的字节码位置,然后修改pc继续执行代码
 * 如果当前方法中没有搜索到对应的异常处理器,则弹出当前栈帧,调用当前栈帧的栈帧恢复执行
 * 继续重复执行抛出异常的操作, 如果当前线程栈没有栈帧存在了,则当前线程退出
 * <p>
 * 注意:如果线程中存在无法捕获的异常,则线程退出,所以新建线程一定要设置线程默认异常处理函数
 *
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class athrow extends OpcodeSupport {
    @Override
    public int opcode() {
        return 191;
    }

    @Override
    public Object operate(StackFrame frame) {

        return null;
    }
}
