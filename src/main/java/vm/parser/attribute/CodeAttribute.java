package vm.parser.attribute;

import vm.parser.*;

import java.io.IOException;

/**
 * 用于存储字节码的属性,读取字节码时,忽略最开始的6字节
 * @author yangqf
 * @version 1.0 2016/3/27
 */

@lombok.Getter
@lombok.Setter
public class CodeAttribute extends AttributeInfoSupport{
//    U2 attribute_name_index;
//    U4 attribute_length;
    U2 max_stack;
    U2 max_locals;
    U4 code_length;
    U1 code[];
    U2 exception_table_length;
    //TODO 异常表
    /**
     * {
     * u2 start_pc;
     * u2 end_pc;
     * u2 handler_pc;
     * u2 catch_type;
     * } exception_table[exception_table_length];
     */
    U2 attribute_count;
    AttributeInfo attributes[];

    ClassFile cf;

    public CodeAttribute(ClassFile cf){
        this.cf = cf;
    }
    @Override
    public void parse(ClassFileReader reader) throws IOException {
//        attribute_name_index = reader.readU2();
//        attribute_length = reader.readU4();
        max_stack = reader.readU2();
        max_locals = reader.readU2();
        code_length = reader.readU4();
        code = new U1[(int) code_length.value];
        reader.readBytes(code);
        exception_table_length = reader.readU2();
        attribute_count = reader.readU2();
        attributes = new AttributeInfo[attribute_count.value];
        for(int i = 0; i < attribute_count.value; i++){
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setCf(cf);
            attributeInfo.parse(reader);
            attributes[i] = attributeInfo;
        }
    }

}
