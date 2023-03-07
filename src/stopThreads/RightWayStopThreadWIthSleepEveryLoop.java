package stopThreads;

/***
 * 如果在执行中，每次循环都会 调用sleep或者wait 方法，那么..不需要在每次迭代中检查中断
 */
public class RightWayStopThreadWIthSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
       // System.out.println("结束2");
    }
}
