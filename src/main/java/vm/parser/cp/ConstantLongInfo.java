package vm.parser.cp;

import vm.parser.*;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantLongInfo implements IConstantPoolObject {
    U1 tag = U1.of(5);
    U1 high_bytes[] = new U1[4];
    U1 low_bytes[] = new U1[4];


    @Override
    public void parse(ClassFileReader reader) throws Exception {
        reader.readBytes(high_bytes);
        reader.readBytes(low_bytes);

    }
}
