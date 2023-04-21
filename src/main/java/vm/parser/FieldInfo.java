package vm.parser;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class FieldInfo {
    U2 access_flags;
    U2 name_index;
    U2 descriptor_index;
    U2 attributes_count;
    AttributeInfo attributes[];

    public void parse(ClassFileReader reader) throws IOException {
        access_flags = reader.readU2();
        name_index = reader.readU2();
        descriptor_index = reader.readU2();
        attributes_count = reader.readU2();
        attributes = new AttributeInfo[attributes_count.value];
        for (int i = 0; i < attributes_count.value; i++) {
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.parse(reader);
            attributes[i] = attributeInfo;
        }
    }
}
