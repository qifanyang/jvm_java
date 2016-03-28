package vm.parser;

/**
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class Utils {

    /**
     * 将常量池对象转换为字节数据
     * @param info
     * @return
     */
//    public static int[] convert2Bytes(Object info) throws Exception {
//
//        Field[] fields = info.getClass().getDeclaredFields();
//        int len = 0;
//        for(Field f : fields){
//            f.setAccessible(true);
//            Object value = f.get(info);
//            if(value instanceof Byte){
//                len+=1;
//            }else if(value instanceof Short){
//                len+=2;
//            }else if(value instanceof Integer){
//                len+=4;
//            }else if(value instanceof int[]){
//                int[] bytes = (int[]) value;
//                len+=bytes.length;
//            }
//        }
//
//        ByteBuffer buffer = ByteBuffer.allocate(len);
//        for(Field f : fields){
//            Object value = f.get(info);
//            if(value instanceof Byte){
//                buffer.put((Byte)value);
//            }else if(value instanceof Short){
//                buffer.putShort((Short) value);
//            }else if(value instanceof Integer){
//                buffer.putInt((Integer) value);
//            }else if(value instanceof int[]){
//                int[] bytes = (int[]) value;
//                for(int b : bytes) {
//                    buffer.putInt(b);
//                }
//            }
//        }
//        return buffer.asIntBuffer().array();
//    }
//
//    public static void readFromDataInput(Object info, DataInput dataInput) throws Exception {
//        //info 字段顺序需要和jvm规范一直,否则会读取错误
//        Field[] fields = info.getClass().getDeclaredFields();
//
//        for(Field f : fields){
//            f.setAccessible(true);
//            Object value = f.get(info);
//            Class<?> type = f.getType();
//            if(type == byte.class){
//                value = dataInput.readByte();
//            }else if(type == short.class){
//                value = dataInput.readShort();
//            }else if(type == int.class){
//                value = dataInput.readInt();
//            }else if(value instanceof byte[]){
//                if(value != null){
//                    dataInput.readFully((byte[]) value);
//                }
//                //如果有可变数组,那么必须有一个 length字段表示字节数组长度
//                Field lengthFiled = info.getClass().getField("length");
//                Object len = lengthFiled.get(info);
//                if(null == len || len == Integer.valueOf(0)){
//                    throw new IllegalStateException("bytes array length is null");
//                }
//                value = new byte[(int) len];
//                dataInput.readFully((byte[]) value);
//            }
//            f.set(info, value);
//        }
//    }
//
//    public static void readUnsignedBytes(int bytes[], DataInput dataInput) throws IOException {
//        for(int i = 0; i < bytes.length; i++){
//            bytes[i] = dataInput.readUnsignedByte();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        ConstantUtf8Info utf8Info = new ConstantUtf8Info();
//        String s = "hello world";
//        byte[] bytes = s.getBytes("utf-8");
//        utf8Info.setLength((short) bytes.length);
////        utf8Info.setBytes(bytes);
//
//        byte[] bytes1 = utf8Info.convert2Bytes();
//        int[] bytes2 = Utils.convert2Bytes(utf8Info);
//        for(byte b1 : bytes1){
//            System.out.print(b1);
//        }
//        System.out.println();
////        for(byte b1 : bytes2){
////            System.out.print(b1);
////        }
//
//    }
}
