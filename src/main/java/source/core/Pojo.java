package source.core;

public class Pojo {

    public void testPublic() {
    }

    public void testPrivate() {
    }

    public static void main(String[] args) {
        Pojo pojo = new Pojo();
    }
}

/**
 * yangqifandeMacBook-Pro:core yangqifan$ javap -verbose Pojo.class
 * Classfile /Users/yangqifan/IdeaProjects/jvm_java/target/classes/source/core/Pojo.class
 * Last modified 2023-4-21; size 477 bytes
 * MD5 checksum 7da7fc91e7b4d6543db6b035142ceac0
 * Compiled from "Pojo.java"
 * public class source.core.Pojo
 * minor version: 0
 * major version: 52
 * flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 * #1 = Methodref          #4.#20         // java/lang/Object."<init>":()V
 * #2 = Class              #21            // source/core/Pojo
 * #3 = Methodref          #2.#20         // source/core/Pojo."<init>":()V
 * #4 = Class              #22            // java/lang/Object
 * #5 = Utf8               <init>
 * #6 = Utf8               ()V
 * #7 = Utf8               Code
 * #8 = Utf8               LineNumberTable
 * #9 = Utf8               LocalVariableTable
 * #10 = Utf8               this
 * #11 = Utf8               Lsource/core/Pojo;
 * #12 = Utf8               test
 * #13 = Utf8               main
 * #14 = Utf8               ([Ljava/lang/String;)V
 * #15 = Utf8               args
 * #16 = Utf8               [Ljava/lang/String;
 * #17 = Utf8               pojo
 * #18 = Utf8               SourceFile
 * #19 = Utf8               Pojo.java
 * #20 = NameAndType        #5:#6          // "<init>":()V
 * #21 = Utf8               source/core/Pojo
 * #22 = Utf8               java/lang/Object
 * {
 * public source.core.Pojo(); //构造方法
 * descriptor: ()V
 * flags: ACC_PUBLIC
 * Code:
 * stack=1(操作数栈最大深度), locals=1(局部变量数量,实例方法第0个参数为this,无参实例方法值为1), args_size=1
 * 0: aload_0 //从local variables加载reference到operand stack, 0是本地变量表的index
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * LineNumberTable:
 * line 3: 0
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       5     0  this   Lsource/core/Pojo;
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=2, locals=2, args_size=1
 * 0: new           #2                  // class source/core/Pojo
 * 3: dup //
 * 4: invokespecial #3                  // Method "<init>":()V
 * 7: astore_1
 * 8: return
 * LineNumberTable:
 * line 9: 0
 * line 10: 8
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       9     0  args   [Ljava/lang/String;
 * 8       1     1  pojo   Lsource/core/Pojo;
 * }
 * SourceFile: "Pojo.java"
 */
