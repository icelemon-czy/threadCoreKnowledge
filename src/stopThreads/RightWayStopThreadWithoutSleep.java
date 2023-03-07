package stopThreads;

/**
 * run 方法内没有sleep或者wait方法 时候，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{
    @Override
    public void run() {
        int num = 0;
        while(!Thread.currentThread().isInterrupted() && num<= Integer.MAX_VALUE/2){
            if(num%10000 == 0){
                System.out.println(num+"是 10000 倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
       thread.start();
       Thread.sleep(2000);
       thread.interrupt();
    }
}
