package vm.parser;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class ClassFileReader {

    private DataInputStream dataInput;

    private int mark;//类似ByteBuffer的mark, 这里用于计算读取了多少数据
    private int position;//类似ByteBuffer的position,记录当前位置

    HexFormat hf = new HexFormat();
    boolean output = false;

    public ClassFileReader(DataInputStream dataInput){
        this.dataInput = dataInput;
        this.mark = 0;
        this.position = 0;
    }

    public U1 readU1() throws IOException {
        int i = dataInput.readUnsignedByte();
        position++;
        if(output){
            hf.formatU1(i);
        }
        return U1.of(i);
    }

    public U2 readU2() throws IOException {
        int i = dataInput.readUnsignedShort();
        position+=2;
        if (output) {
            hf.formatU2(U2.of(i));
        }
        return U2.of(i);
    }

    /**
     * 2^31=2147483648=2G, 单个字节码不可能有这么大,所以使用有符号的int,读取无符号4字节不会发生越界
     * @return
     * @throws IOException
     */
    public U4 readU4() throws IOException {
        int i = dataInput.readInt();
        position+=4;
        if (output) {
            hf.formatU4(U4.of(i));
        }
        return U4.of(i);
    }

    public void readBytes(U1[] u1s) throws IOException {
        for(int i = 0; i < u1s.length; i++){
            u1s[i] = readU1();
        }
    }

    public void skip(U4 u4) throws IOException{
        for(int i = 0; i < u4.value; i++){
            readU1();
        }
    }

    public void mark(){
        this.mark = position;
    }

    public int markValue(){
        return this.mark;
    }

    public int position(){
        return this.position;
    }

    public void close(){
        try {
            dataInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
