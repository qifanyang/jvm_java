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
public class ConstantInvokeDynamicInfo implements IConstantPoolObject {
    U1 tag = U1.of(18);
    U2 bootstrap_method_attr_index;
    U2 name_and_type_index;


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        bootstrap_method_attr_index = reader.readU2();
        name_and_type_index = reader.readU2();
    }
}
