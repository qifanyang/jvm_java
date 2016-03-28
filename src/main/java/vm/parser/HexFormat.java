package vm.parser;

/**
 * 用于解析class文件调试,输出十六进制,
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class HexFormat {

    private int offset;//换行
    private long pause = 2000;//输出间隔时间,调试
    private boolean shouldPause = false;
    //u1-->0 , u2-->1, u4-->2, 索引大的值有一个为true,则当前不暂停
    private boolean pausePk[] = new boolean[3];

    /**
     * 格式化输出无符号byte,截取低8位
     * @param s
     */
    public void formatU1(int s){
//        byte high = (byte) ((s & 0xF0) >> 8);
        short b = (short) (s & 0xFF);
        format();
        String hexString = Integer.toHexString(b).toUpperCase();
        if(hexString.length() == 1){
            hexString = "0"+hexString;
        }
        System.out.print(hexString);
        offset++;
    }

    /**
     * 格式化输出无符号short,截取低16位
     * @param u2
     */
    public void formatU2(U2 u2){
        int i = u2.value;
        short high = (short) ((i & 0xFF00) >> 8);
        short low = (short) (i & 0xFF);
        formatU1(high);
        formatU1(low);
    }

    public void formatU4(U4 u4){
        long i = u4.value;
        int high = (int) ((i & 0xFFFF0000) >> 16);//
        int low = (int) (i & 0xFFFF);
        formatU2(U2.of(high));
        formatU2(U2.of(low));
    }

    public void format(int[] bytes){
        for(int b : bytes){
            formatU1(b);
        }
    }

    private void pauseConvert(){
        if(shouldPause){
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        shouldPause = !shouldPause;
    }

    private void format(){
        if(offset % 16 == 0){
            System.out.println();
            System.out.print(" ");
        }else {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        HexFormat hexFormat = new HexFormat();
        hexFormat.formatU2(U2.of(51966));
        hexFormat.formatU2(U2.of(51966));
        hexFormat.formatU2(U2.of(51966));
        hexFormat.formatU2(U2.of(51966));
        hexFormat.formatU2(U2.of(51966));
        hexFormat.formatU2(U2.of(51966));

    }
}
