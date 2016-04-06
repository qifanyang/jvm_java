package vm.parser.attribute;

import vm.parser.*;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/28
 */
@lombok.Getter
@lombok.Setter
public class LineNumberTableAttribute extends AttributeInfoSupport{
//    U2 attribute_name_index;
//    U4 attribute_length;
    U2 line_number_table_length;
    LineNumberTable line_number_table[];

    ClassFile cf;
    public LineNumberTableAttribute(ClassFile cf){
        this.cf = cf;
    }

    @lombok.Data
    static class LineNumberTable{
        U2 start_pc;
        U2 line_number;
    }

    @Override
    public void parse(ClassFileReader reader) throws IOException {
//        attribute_name_index = reader.readU2();
//        attribute_length = reader.readU4();
        line_number_table_length = reader.readU2();
        line_number_table = new LineNumberTable[line_number_table_length.value];

        for(int i = 0; i < line_number_table_length.value; i++){
            LineNumberTable lineNumberTable = new LineNumberTable();
            lineNumberTable.setStart_pc(reader.readU2());
            lineNumberTable.setLine_number(reader.readU2());
            line_number_table[i] = lineNumberTable;
        }
    }
}
