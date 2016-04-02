package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantFieldrefInfo implements IConstantPoolObject {
    U1 tag = U1.of(9);
    U2 class_index;//指向常量池的索引,值类型ClassInfo
    U2 name_and_type_index;//指向常量池的索引,name_and_type_info

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        class_index = reader.readU2();
        name_and_type_index = reader.readU2();
    }

    public String string(){

        return "";
    }

}
