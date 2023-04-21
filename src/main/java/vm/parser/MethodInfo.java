package vm.parser;

import vm.parser.cp.ConstantPoolInfo;

import java.io.IOException;

/**
 * java方法字节码数据结构
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class MethodInfo {
    U2 access_flags;
    U2 name_index;
    U2 descriptor_index;
    U2 attributes_count;
    AttributeInfo attributes[];

    ClassFile cf;

    public void parse(ClassFileReader reader) throws IOException {
        access_flags = reader.readU2();
        name_index = reader.readU2();
        descriptor_index = reader.readU2();
        attributes_count = reader.readU2();
        attributes = new AttributeInfo[attributes_count.value];
        for (int i = 0; i < attributes_count.value; i++) {
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setCf(cf);
            attributeInfo.parse(reader);
            attributes[i] = attributeInfo;
        }
    }

    @Override
    public String toString() {

        ConstantPoolInfo constantPoolInfo = cf.getConstant_pool_info()[name_index.value];

        return constantPoolInfo.getConstantPoolObject().toString();
    }
}
