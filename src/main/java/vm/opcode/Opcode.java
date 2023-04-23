package vm.opcode;

import vm.runtime.StackFrame;

/**
 * 字节码指令, 使用单个字节存储, 最多255个, 目前有3个保留指令
 * @author yangqf
 * @version 1.0 2016/4/2
 */
public interface Opcode {

    int opcode();

    /**
     * 执行指令,主要就是操作本地变量表locals和操作数栈Operand
     * 注意事项:
     * 1.字节码执行期间pc值为执行当前字节码
     * 2.字节码执行完毕,更新pc, 应该用一个数组存储字节码操作数长度
     *
     * @param frame
     * @return
     */
    Object operate(StackFrame frame);
}
