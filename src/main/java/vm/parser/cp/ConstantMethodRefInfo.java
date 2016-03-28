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
@lombok.Data
public class ConstantMethodRefInfo implements IConstantPoolObject {
    U1 tag = U1.of(10);
    U2 class_index;
    U2 name_and_type_index;

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        class_index = reader.readU2();
        name_and_type_index = reader.readU2();
    }

}
