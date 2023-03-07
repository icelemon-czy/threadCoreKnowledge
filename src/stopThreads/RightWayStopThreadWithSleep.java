package stopThreads;

/**
 * 带有sleep的中断写法
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100倍数");
                    }
                    num++;
                 //   System.out.println(num);
                 //   Thread.sleep(1000);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("结束");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(500);
        thread.interrupt();
    }
}
