package vm.parser.cp;

import vm.parser.U1;

/**
 * 常量池tag枚举
 * @author yangqf
 * @version 1.0 2016/3/28
 */
public enum TagEnum {
    CONSTANT_Utf8(1),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_Class(7),
    CONSTANT_String(8),
    CONSTANT_FieldRef(9),
    CONSTANT_MethodRef(10),
    CONSTANT_InterfaceMethodRef(11),
    CONSTANT_NameAndType(12),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_InvokeDynamic(18);


    private U1 value;
    private TagEnum(int value) {
        this.value = U1.of(value);
    }

    public U1 getValue(){
        return value;
    }
}
