package vm.parser.attribute;

import vm.parser.ClassFileReader;
import vm.parser.U2;
import vm.parser.U4;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/4/6
 */
@lombok.Getter
@lombok.Setter
public class StackMapTableAttribute extends AttributeInfoSupport{
//    U2 attribute_name_index;
//    U4 attribute_length;
    U2 number_of_entries;
    StackMapFrame entries[];
    static class StackMapFrame{

    }
    @Override
    public void parse(ClassFileReader reader) throws IOException{
            //跳过,暂时不处理
            reader.skip(attribute_length);
    }
}
