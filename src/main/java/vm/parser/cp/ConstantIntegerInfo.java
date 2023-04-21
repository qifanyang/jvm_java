package vm.parser.cp;

import vm.parser.ClassFileHolder;
import vm.parser.ClassFileReader;
import vm.parser.ConstantPoolObject;
import vm.parser.U1;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantIntegerInfo extends ConstantPoolObject {
    U1 tag = U1.of(3);
    U1 bytes[] = new U1[4];//Big-Endian , byte short boolean char 都用integer表示


    @Override
    public void parse(ClassFileReader reader) throws IOException {
        reader.readBytes(bytes);
    }
}
