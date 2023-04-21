package vm.opcode;

import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public interface Opcode {

    public int opcode();

    /**
     * 注意事项:
     * 1.字节码执行期间pc值执行当前字节码
     * 2.字节码执行完毕,更新pc, 应该用一个数组存储字节码操作数长度
     *
     * @param frame
     * @return
     */
    public Object operate(StackFrame frame);
}
