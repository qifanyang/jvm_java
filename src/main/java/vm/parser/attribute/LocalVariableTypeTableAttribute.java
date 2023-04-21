package vm.parser.attribute;

import vm.parser.ClassFileReader;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/4/15
 */
public class LocalVariableTypeTableAttribute extends AttributeInfoSupport {

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        reader.skip(getAttribute_length());
    }
}
