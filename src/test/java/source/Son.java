package source;

import vm.lib.Log;

/**
 * @author yangqf
 * @version 1.0 2016/4/8
 */
public class Son extends Father{

    public void say(){
        Log.say("i am son ");
        fatherSay();//protected public 方法可以继承 使用invokevirtual调用, 调用从当前对象属于的类开始递归查找方法
        super.fatherSay();//当前类有和父类名字和描述符都相同的方法时,调用父类方法需要用super.xxx()  字节码使用invokespecial调用
        privateSay();//私有方式使用invokespecial
    }

    public void fatherSay(){
        Log.say("重写父类方法:fathersay");
    }

    private void privateSay(){
        Log.say("i am private method ");
    }

    public static void main(String[] args){
        Father son = new Son();
        son.say();
    }
}
