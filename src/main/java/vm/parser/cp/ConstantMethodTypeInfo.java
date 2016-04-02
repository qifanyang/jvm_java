package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 *表示方法类型,这里和name_and_type有点重复
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantMethodTypeInfo implements IConstantPoolObject {
    U1 tag = U1.of(16);
    U2 descriptor_index;

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        descriptor_index = reader.readU2();
    }
}
