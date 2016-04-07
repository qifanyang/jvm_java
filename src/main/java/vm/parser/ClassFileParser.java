package vm.parser;

import vm.parser.cp.*;

import java.io.*;

/**
 * Title:class文件解析器
 *
 * @author yangqf
 * @version 1.0 2016/3/26
 */
public class ClassFileParser {
    private ClassFile cf;
    private ClassFileReader reader;
    public ClassFileParser(DataInputStream dataInput) {
        cf = new ClassFile();
        reader = new ClassFileReader(dataInput);
    }

    public void parse() throws Exception {
        magic();
        minorVersion();
        majorVersion();
        constantPool();
        accessFlag();
        thisClass();
        superClass();
        interfaces();
        fields();
        methods();
        attributes();
    }

    public void magic() throws IOException {
        cf.setMagic(reader.readU4());
    }

    public void minorVersion() throws IOException {
        cf.setMinor_version(reader.readU2());
    }

    public void majorVersion() throws IOException {
        cf.setMajor_version(reader.readU2());
    }

    public void constantPoolCount() throws IOException {
        cf.setConstant_pool_count(reader.readU2());
    }

    /**
     * 解析常量池
     */
    public void constantPool() throws Exception {
        constantPoolCount();
        U2 constant_pool_count = cf.getConstant_pool_count();
        //第0个不存储?
        cf.setConstant_pool_info(new ConstantPoolInfo[constant_pool_count.value + 1]);
        ConstantPoolInfo[] constant_pool_info = cf.getConstant_pool_info();
        //常量池的索引范围是1至constant_pool_count−1
        for(int i = 1; i < constant_pool_count.value; i++){
            ConstantPoolInfo cpInfo = new ConstantPoolInfo();
            ConstantPoolObject constantPoolObject = null;
            U1 tag = reader.readU1();//常量池tag
            switch (tag.value){
                case 1:
                    //constant_utf8_info
                    constantPoolObject = new ConstantUtf8Info();
                    break;
                case 2:
                case 3:
                    //constant_integer
                    constantPoolObject = new ConstantIntegerInfo();
                    break;
                case 4:
                    //constant_float
                    constantPoolObject = new ConstantFloatInfo();
                    break;
                case 5:
                    //constant_long
                    constantPoolObject = new ConstantLongInfo();
                    break;
                case 6:
                    //constant_double
                    constantPoolObject = new ConstantDoubleInfo();
                    break;
                case 7:
                    //constant_class
                    constantPoolObject = new ConstantClassInfo();
                    break;
                case 8:
                    //constant_string
                    constantPoolObject = new ConstantStringInfo();
                    break;
                case 9:
                    //constant_fieldref
                    constantPoolObject = new ConstantFieldrefInfo();
                    break;
                case 10:
                    //constant_methodref
                    constantPoolObject = new ConstantMethodRefInfo();
                    break;
                case 11:
                    //constant_interfaceref
                    constantPoolObject = new ConstantInterfaceMethodRefInfo();
                    break;
                case 12:
                    //constant_nameAndType
                    constantPoolObject = new ConstantNameAndTypeInfo();
                    break;
                case 13:
                    break;
                case 15:
                    //constant_methodHandle
                    constantPoolObject = new ConstantMethodHandleInfo();
                    break;
                case 16:
                    //constant_methodType
                    constantPoolObject = new ConstantMethodTypeInfo();
                    break;
                case 18:
                    //constant_invokeDynamic
                    constantPoolObject = new ConstantInvokeDynamicInfo();
                    break;
                default:
                    throw new IllegalStateException("constant pool tag is illegal!!!, tag = " + tag.value + " index = " + i);
            }
            constantPoolObject.parse(reader);
            cpInfo.setTag(constantPoolObject.getTag());
            cpInfo.setConstantPoolObject(constantPoolObject);
//            cpInfo.setInfo(Utils.convert2Bytes(constantPoolParser));
            constant_pool_info[i] = cpInfo;
        }

    }


    public void accessFlag() throws IOException {
        cf.setAccess_flags(reader.readU2());
    }

    public void thisClass() throws IOException {
        cf.setThis_class(reader.readU2());
    }

     public void superClass() throws IOException {
        cf.setSuper_class(reader.readU2());
    }

    public void interfacesCount() throws IOException {
        cf.setInterfaces_count(reader.readU2());
    }

    public void interfaces() throws IOException {
        interfacesCount();
        U2 interfaces_count = cf.getInterfaces_count();
        U2 interfaces[] =new U2[interfaces_count.value];
        for(int i = 0; i < interfaces_count.value; i++){
            interfaces[i] = reader.readU2();
        }
        cf.setInterfaces(interfaces);
    }

    public void fieldsCount() throws IOException {
        cf.setFields_count(reader.readU2());
    }

    public void fields() throws IOException {
        fieldsCount();
        U2 fields_count = cf.getFields_count();
        FieldInfo fields[] = new FieldInfo[fields_count.value];
        cf.setFields(fields);
        for(int i = 0; i < fields_count.value; i++) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.parse(reader);
            fields[i] = fieldInfo;
        }
    }

    public void methodsCount() throws IOException {
        cf.setMethods_count(reader.readU2());
    }

    public void methods() throws IOException {
        methodsCount();
        U2 methods_count = cf.getMethods_count();
        MethodInfo methods[] = new MethodInfo[methods_count.value];
        cf.setMethods(methods);
        for(int i = 0; i < methods_count.value; i++){
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setCf(cf);
            methodInfo.parse(reader);
            methods[i] = methodInfo;
        }
    }

    public void attributesCount() throws IOException {
        cf.setAttributes_count(reader.readU2());
    }

    public void attributes() throws IOException {
        attributesCount();
        U2 attributes_count = cf.getAttributes_count();
        AttributeInfo attributes[] = new AttributeInfo[attributes_count.value];
        cf.setAttributes(attributes);

        for(int i = 0; i < attributes_count.value; i++){
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setCf(cf);
            attributeInfo.parse(reader);
            attributes[i] = attributeInfo;
        }
    }

    public static ClassFile load(String path){
        try{
            File file = null;
            if(path.startsWith("java")){
              file = new File(System.getProperty("user.dir") + "\\rt\\" + path + ".class").getCanonicalFile();
            }else {
              file = new File(System.getProperty("user.dir") + "\\target\\classes\\" + path + ".class").getCanonicalFile();
            }
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dataInput = new DataInputStream(fis);
            ClassFileParser parser = new ClassFileParser(dataInput);
            parser.parse();
            return parser.cf;
        }catch(Exception e){
            e.printStackTrace();
            new IllegalStateException("加载类失败, url =" + path);
        }
        throw  new IllegalStateException("加载类失败, url =" + path);
    }
    public static void main(String[] args) throws Exception {
//        InputStream in = ClassFileParser.class.getClassLoader().getResourceAsStream("");
        File file = new File(System.getProperty("user.dir") + "\\target\\classes\\test\\source\\A.class").getCanonicalFile();
        System.out.println("file = " + file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dataInput = new DataInputStream(fis);
        ClassFileParser parser = new ClassFileParser(dataInput);
        parser.parse();
        System.out.println();
//        System.out.println(parser.reader.position());
        System.out.println("字段数量 = "+ parser.cf.getFields_count());
        System.out.println("方法数量 = " + parser.cf.getMethods_count());
        MethodInfo[] methods = parser.cf.getMethods();
        for(MethodInfo methodInfo : methods){
            ConstantUtf8Info info = (ConstantUtf8Info)parser.cf.getConstant_pool_info()[methodInfo.name_index.value].getConstantPoolObject();
            System.out.println(info.string());
        }
    }
}
