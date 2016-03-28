package vm.parser.attribute;

import vm.parser.*;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/28
 */
@lombok.Data
public class LocalVariableTableAttribute implements IAttributeObject{
    U2 attribute_name_index;
    U4 attribute_length;
    U2 local_variable_table_length;
    LocalVariableTable local_variable_tables[];

    ClassFile cf;
    public LocalVariableTableAttribute(ClassFile cf){
        this.cf = cf;
    }

    @lombok.Data
    static class LocalVariableTable{
        U2 start_pc;
        U2 length;
        U2 name_index;
        U2 descriptor_index;
        U2 index;
    }

    @Override
    public void parse(ClassFileReader reader) throws IOException {
//        attribute_name_index = reader.readU2();
//        attribute_length = reader.readU4();
        local_variable_table_length = reader.readU2();
        local_variable_tables = new LocalVariableTable[local_variable_table_length.value];

        for(int i = 0; i < local_variable_table_length.value; i++){
            LocalVariableTable localVariableTable = new LocalVariableTable();
            localVariableTable.setStart_pc(reader.readU2());
            localVariableTable.setLength(reader.readU2());
            localVariableTable.setName_index(reader.readU2());
            localVariableTable.setDescriptor_index(reader.readU2());
            localVariableTable.setIndex(reader.readU2());
            local_variable_tables[i] = localVariableTable;
        }
    }

}
