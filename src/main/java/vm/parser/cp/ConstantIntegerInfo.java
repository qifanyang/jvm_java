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
public class ConstantIntegerInfo implements IConstantPoolObject {
    U1 tag = U1.of(3);
    U1 bytes[] = new U1[4];//Big-Endian , byte short boolean char 都用integer表示


    @Override
    public void parse(ClassFileReader reader) throws Exception {
        reader.readBytes(bytes);
    }
}
