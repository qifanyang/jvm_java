package vm.parser;

import vm.parser.cp.ConstantPoolInfo;

/**
 * 字节码数据结构
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ClassFile {
    private U4 magic;
    private U2 minor_version;
    private U2 major_version;
    private U2 constant_pool_count;
    private ConstantPoolInfo constant_pool_info[];
    private U2 access_flags;
    private U2 this_class;
    private U2 super_class;
    private U2 interfaces_count;
    private U2 interfaces[];
    private U2 fields_count;
    private FieldInfo fields[];
    private U2 methods_count;
    private MethodInfo methods[];
    private U2 attributes_count;
    private AttributeInfo attributes[];

}
