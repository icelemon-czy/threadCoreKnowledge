package ThreaedPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService =Executors.newScheduledThreadPool(10);
       //  executorService.schedule(new Task(),5, TimeUnit.SECONDS);
        //重复运行
        executorService.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
