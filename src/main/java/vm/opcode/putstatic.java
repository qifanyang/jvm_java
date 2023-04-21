package vm.opcode;

import vm.parser.cp.ConstantClassInfo;
import vm.parser.cp.ConstantFieldrefInfo;
import vm.parser.cp.ConstantNameAndTypeInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.RTClass;
import vm.runtime.RTMethodArea;
import vm.runtime.RTObject;
import vm.runtime.StackFrame;

/**
 * 静态字段设值
 *
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class putstatic extends OpcodeSupport {
    @Override
    public int opcode() {
        return 179;
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 2);//fieldref
        ConstantFieldrefInfo constantFieldrefInfo = indexConstantPoolObject(frame, operand, ConstantFieldrefInfo.class);

        ConstantClassInfo constantClassInfo = indexConstantPoolObject(frame, constantFieldrefInfo.getClass_index(), ConstantClassInfo.class);
        ConstantUtf8Info classNameInfo = indexConstantPoolObject(frame, constantClassInfo.getName_index(), ConstantUtf8Info.class);

        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, constantFieldrefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        ConstantUtf8Info fieldNameInfo = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class);
        Object value = frame.getOperands().pop();
        RTClass rtClass = RTMethodArea.loadClass(classNameInfo.string());
        rtClass.getStaticFields().put(fieldNameInfo.string(), value);

        return null;
    }
}
