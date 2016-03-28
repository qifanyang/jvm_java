package vm.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量池解析器注册
 * @author yangqf
 * @version 1.0 2016/3/27
 */
public class ConstantPoolParserRegistry {

    private static Map<Integer, IConstantPoolObject> parserMap = new HashMap<>();

    public static void registry(Integer tag, IConstantPoolObject parser) {
        parserMap.put(tag, parser);
    }

    public static IConstantPoolObject fetch(Integer tag) {
        return parserMap.get(tag);
    }
}
