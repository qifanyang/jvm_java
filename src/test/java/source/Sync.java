package source;

/**
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class Sync{
    public synchronized void s(){
//        synchronized(this){ // 解析bug
//        }
    }

    public static void main(String[] args){
        Sync sync = new Sync();
        sync.s();
    }
}
