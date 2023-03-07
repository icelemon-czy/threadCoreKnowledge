package ThreaedPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  演示newFixedThreadPool 出错情况
 */
public class FixThreadPoolOOM {

    public static void main(String[] args) {
        ExecutorService fix = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            fix.execute(new SubThread());
        }
    }
}
class SubThread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
