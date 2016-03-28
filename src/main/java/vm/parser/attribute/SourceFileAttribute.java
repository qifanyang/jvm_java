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
public class SourceFileAttribute implements IAttributeObject {
    U2 attribute_name_index;
    U4 attribute_length;
    U2 source_file_index;

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        attribute_name_index = reader.readU2();
        attribute_length = reader.readU4();
        source_file_index = reader.readU2();
    }
}
