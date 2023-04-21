package vm.parser.attribute;

import vm.parser.ClassFileReader;

import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/4/7
 */
public class ExceptionsAttribute extends AttributeInfoSupport {
    @Override
    public void parse(ClassFileReader reader) throws IOException {
        //skip
        reader.skip(attribute_length);

    }
}
