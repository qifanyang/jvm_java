package vm.parser.attribute;

import vm.parser.*;

import java.io.IOException;

/**
 * 具体attribute不读取name_index和length因为在外层attribute已经读取
 * @author yangqf
 * @version 1.0 2016/3/28
 */
@lombok.Getter
@lombok.Setter
public class SourceFileAttribute extends AttributeInfoSupport{
//    U2 attribute_name_index;
//    U4 attribute_length;
    U2 source_file_index;

    ClassFile cf;
    public SourceFileAttribute(ClassFile cf){
        this.cf = cf;
    }

    @Override
    public void parse(ClassFileReader reader) throws IOException {
//        attribute_name_index = reader.readU2();
//        attribute_length = reader.readU4();
        source_file_index = reader.readU2();
    }
}
