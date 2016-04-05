package vm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqf
 * @version 1.0 2016/4/5
 */
public class DescriptorUtil{
    public static Class<?>[] fromDescriptor(String descriptor){
        //(Ljava/lang/String;)V
        //(ILjava/lang/String;)V
        List<Class<?>> list = new ArrayList<>();
        char[] chars = descriptor.toCharArray();
        for(int i = 1; i < chars.length; i++){
            char c = chars[i];
            if(c == 'I'){
                list.add(Integer.class);
            }else if(c == 'L'){
                list.add(String.class);
                i+= 16;
            }
        }

        return list.toArray(new Class<?>[list.size()]);
    }
}
