package source;

import vm.lib.Log;

/**
 * @author yangqf
 * @version 1.0 2016/4/8
 */
public class Son extends Father{

    public void sonSay(){
        Log.say("i am son ");
        protecMethod();//protected public 方法可以继承 使用invokevirtual调用
    }
}
