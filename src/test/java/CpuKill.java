/**
 * @author yangqf
 * @version 1.0 2016/4/9
 */
public class CpuKill{
    public static void main(String[] args){
        int processors = Runtime.getRuntime().availableProcessors();

        for(int i = 0; i < processors-1; i++){
            new Thread(){
                @Override
                public void run(){
                    while(true){
                        setDaemon(true);
                    }
                }
            }.start();
        }
    }
}
