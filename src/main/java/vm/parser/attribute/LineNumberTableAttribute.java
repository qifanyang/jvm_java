package vm.parser.attribute;

import vm.parser.ClassFileReader;
import vm.parser.IAttributeObject;
import vm.parser.U2;
import vm.parser.U4;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/28
 */
@lombok.Data
public class LineNumberTableAttribute implements IAttributeObject{
    U2 attribute_name_index;
    U4 attribute_length;
    U2 line_number_table_length;
    LineNumberTable line_number_table[];

    @lombok.Data
    static class LineNumberTable{
        U2 start_pc;
        U2 line_number;
    }

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        attribute_name_index = reader.readU2();
        attribute_length = reader.readU4();
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
