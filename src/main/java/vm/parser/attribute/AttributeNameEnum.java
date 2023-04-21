package vm.parser.attribute;

/**
 * @author yangqf
 * @version 1.0 2016/3/28
 */
public enum AttributeNameEnum {
    Code("Code"),
    LineNumberTable("LineNumberTable"),
    LocalVariableTable("LocalVariableTable"),
    SourceFile("SourceFile");

    private String name;

    private AttributeNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
