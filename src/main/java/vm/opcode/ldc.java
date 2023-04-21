package vm.opcode;

import vm.parser.ConstantPoolObject;
import vm.parser.cp.ConstantFloatInfo;
import vm.parser.cp.ConstantIntegerInfo;
import vm.parser.cp.ConstantStringInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.StackFrame;

/**
 * 索引的常量池元素可以是int,float,string,指向类的符号引用,指向methodtype的符号引用, 指向methodhandle的符号引用
 *
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class ldc extends OpcodeSupport {
    @Override
    public int opcode() {
        return 18;
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 1);

        ConstantPoolObject constantPoolObject = indexConstantPoolObject(frame, operand, ConstantPoolObject.class);
        if (constantPoolObject instanceof ConstantStringInfo) {
            ConstantStringInfo stringInfo = (ConstantStringInfo) constantPoolObject;
            ConstantUtf8Info utf8Info = indexConstantPoolObject(frame, stringInfo.getString_index(), ConstantUtf8Info.class);
            frame.getOperands().push(utf8Info.toString());
        } else if (constantPoolObject instanceof ConstantIntegerInfo) {

        } else if (constantPoolObject instanceof ConstantFloatInfo) {

        }
        return null;
    }
}
