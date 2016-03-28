package vm.parser;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class ClassFileReader {

    private DataInput dataInput;

    private int mark;//类似ByteBuffer的mark, 这里用于计算读取了多少数据
    private int position;//类似ByteBuffer的position,记录当前位置

    public ClassFileReader(DataInput dataInput){
        this.dataInput = dataInput;
        this.mark = 0;
        this.position = 0;
    }

    public U1 readU1() throws IOException {
        int i = dataInput.readUnsignedByte();
        return U1.of(i);
    }

    public U2 readU2() throws IOException {
        int i = dataInput.readUnsignedShort();
        return U2.of(i);
    }

    public U4 readU4() throws IOException {
        int i = dataInput.readInt();
        return U4.of(i);
    }

    public void readBytes(U1[] u1s) throws IOException {
        for(int i = 0; i < u1s.length; i++){
            u1s[i] = readU1();
        }
    }
}
