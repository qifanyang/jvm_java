package vm.parser.attribute;

import vm.parser.ClassFileReader;
import vm.parser.U2;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
@lombok.Setter
@lombok.Getter
public class SignatureAttribute extends AttributeInfoSupport{
    U2 signature_index;

    @Override
    public void parse(ClassFileReader reader) throws IOException{
        signature_index = reader.readU2();
    }
}
