package vm.parser.attribute;

import lombok.Data;
import lombok.NoArgsConstructor;
import vm.parser.*;

import java.io.IOException;

/**
 *
 * @author yangqf
 * @version 1.0 2016/10/31
 */
@Data
@NoArgsConstructor
public class InnerClassesAttribute extends AttributeInfoSupport{
//    InnerClasses_attribute {
//        u2 attribute_name_index;
//        u4 attribute_length;
//        u2 number_of_classes;
//        { u2 inner_class_info_index;
//            u2 outer_class_info_index;
//            u2 inner_name_index;
//            u2 inner_class_access_flags;
//        } classes[number_of_classes];
//    }
    U2 attribute_name_index;
    U4 attribute_length;
    U2 number_of_classes;
    Classes classes[];
    static class Classes implements Parser{
        U2 inner_class_info_index;
        U2 outer_class_info_index;
        U2 inner_name_index;
        U2 inner_class_access_flags;

        @Override
        public void parse(ClassFileReader reader) throws IOException{
            inner_class_info_index = reader.readU2();
            outer_class_info_index = reader.readU2();
            inner_name_index = reader.readU2();
            inner_class_access_flags = reader.readU2();
        }
    }

    @Override
    public void parse(ClassFileReader reader) throws IOException{
        number_of_classes = reader.readU2();
        classes = new Classes[number_of_classes.value];
        for(int i = 0; i < number_of_classes.value; i++){
            classes[i] = new Classes();
            classes[i].parse(reader);
        }

    }
}
