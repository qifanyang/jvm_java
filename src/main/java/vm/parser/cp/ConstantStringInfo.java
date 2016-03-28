package vm.parser.cp;

import vm.parser.ClassFileReader;
import vm.parser.IConstantPoolObject;
import vm.parser.U1;
import vm.parser.U2;

import java.io.IOException;

/**
 * 用于表示字符串对象java.lang.String
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Data
public class ConstantStringInfo implements IConstantPoolObject {
    U1 tag = U1.of(8);
    U2 string_index;//指向constant_utf8_info的索引

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        string_index = reader.readU2();
    }
}
