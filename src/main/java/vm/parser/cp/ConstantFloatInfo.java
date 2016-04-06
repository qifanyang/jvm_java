package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.ConstantPoolObject;
import vm.parser.U1;

import java.io.IOException;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantFloatInfo  implements ConstantPoolObject{
    U1 tag = U1.of(4);
    U1 bytes[] = new U1[4];//Big-Endian


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        reader.readBytes(bytes);
    }
}
