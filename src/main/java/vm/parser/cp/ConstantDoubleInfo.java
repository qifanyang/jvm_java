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
public class ConstantDoubleInfo  implements ConstantPoolObject{
    U1 tag = U1.of(6);
    U1 high_bytes[] = new U1[4];
    U1 low_bytes[] = new U1[4];

    public void parse(ClassFileReader reader) throws IOException {
        reader.readBytes(high_bytes);
        reader.readBytes(low_bytes);
    }

}
