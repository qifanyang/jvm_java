package vm.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量池解析器注册
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class ConstantPoolParserRegistry {

    private static Map<Integer, ConstantPoolObject> parserMap = new HashMap<>();

    public static void registry(Integer tag, ConstantPoolObject parser) {
        parserMap.put(tag, parser);
    }

    public static ConstantPoolObject fetch(Integer tag) {
        return parserMap.get(tag);
    }
}
