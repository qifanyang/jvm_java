package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantFloatInfo  implements IConstantPoolObject {
    U1 tag = U1.of(4);
    U1 bytes[] = new U1[4];//Big-Endian


    @Override
    public void parse(ClassFileReader reader) throws Exception {
        reader.readBytes(bytes);
    }
}
