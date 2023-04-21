package vm.opcode;

import vm.parser.cp.ConstantFieldrefInfo;
import vm.parser.cp.ConstantNameAndTypeInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.RTObject;
import vm.runtime.StackFrame;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class getfield extends OpcodeSupport {
    @Override
    public int opcode() {
        return 180;//0xb4
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 2);//fieldref
        ConstantFieldrefInfo constantFieldrefInfo = indexConstantPoolObject(frame, operand, ConstantFieldrefInfo.class);
        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, constantFieldrefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        ConstantUtf8Info fieldNameInfo = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class);
        Object objectRef = frame.getOperands().pop();
        RTObject rtObject = (RTObject) objectRef;
        Object value = rtObject.getInsVariableMap().get(fieldNameInfo.string());
        frame.getOperands().push(value);
        return null;
    }
}
