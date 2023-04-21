package vm.opcode;

import vm.parser.cp.ConstantFieldrefInfo;
import vm.parser.cp.ConstantNameAndTypeInfo;
import vm.parser.cp.ConstantUtf8Info;
import vm.runtime.RTObject;
import vm.runtime.StackFrame;

/**
 * putfield      #4                  // Field t:LTest;
 * 给当前实例对象属性设值,操作数索引指向的常量池数据结构为fieldref,
 * 根据属性的描述符确定如何设值,
 * 如果是基本类型byte,boolean,short,char设值为int
 * 如果是引用类型,
 *
 * @author yangqf
 * @version 1.0 2016/4/4
 */
public class putfield extends OpcodeSupport {
    @Override
    public int opcode() {
        return 181;//0xb5
    }

    @Override
    public Object operate(StackFrame frame) {
        int operand = fetchOperand(frame, 2);//fieldref
        ConstantFieldrefInfo constantFieldrefInfo = indexConstantPoolObject(frame, operand, ConstantFieldrefInfo.class);

        ConstantNameAndTypeInfo nameAndTypeInfo = indexConstantPoolObject(frame, constantFieldrefInfo.getName_and_type_index(), ConstantNameAndTypeInfo.class);
        ConstantUtf8Info fieldNameInfo = indexConstantPoolObject(frame, nameAndTypeInfo.getName_index(), ConstantUtf8Info.class);
        Object value = frame.getOperands().pop();
        Object objetctRef = frame.getOperands().pop();
        RTObject rtObject = (RTObject) objetctRef;
        rtObject.getInsVariableMap().put(fieldNameInfo.string(), value);

        return null;
    }
}
