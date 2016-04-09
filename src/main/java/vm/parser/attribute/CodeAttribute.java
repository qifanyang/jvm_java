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
    ExceptionTable exceptionTables[];
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

    static class ExceptionTable{
        U2 start_pc;//包含
        U2 end_pc;//不包含
        U2 handler_pc;
        U2 catch_type;
    }

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
        if(exception_table_length.value != 0){
            exceptionTables = new ExceptionTable[exception_table_length.value];
            for(int i = 0; i < exception_table_length.value; i++){
                ExceptionTable exceptionTable = new ExceptionTable();
                exceptionTable.start_pc = reader.readU2();
                exceptionTable.end_pc = reader.readU2();
                exceptionTable.handler_pc = reader.readU2();//要跳转到的pc位置
                exceptionTable.catch_type = reader.readU2();//异常类型,指向常量池索引
                exceptionTables[i] = exceptionTable;

            }
        }
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
