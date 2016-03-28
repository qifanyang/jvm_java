package vm.parser;

import java.io.IOException;

/**
 * 可解析的
 * @author yangqf
 * @version 1.0 2016/3/28
 */
public interface Parser extends ClassFileHolder{
    public void parse(ClassFileReader reader) throws IOException;
}
