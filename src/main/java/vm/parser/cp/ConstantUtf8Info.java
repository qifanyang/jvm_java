package vm.parser.cp;

import vm.parser.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
@lombok.Getter
@lombok.Setter
public class ConstantUtf8Info implements IConstantPoolObject {
    U1 tag = U1.of(1);
    U2 length;//bytes长度
    U1 bytes[];//utf8编码的字节数组

    @Override
    public void parse(ClassFileReader reader) throws IOException {
        length = reader.readU2();
        bytes = new U1[length.value];
        reader.readBytes(bytes);
    }

    public String string(){
        if(null == bytes || bytes.length == 0){
            return "";
        }
        byte[] byteBytes = new byte[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            byteBytes[i] = (byte) bytes[i].value;
        }
        try {
            return new String(byteBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
        return string();
    }
}
