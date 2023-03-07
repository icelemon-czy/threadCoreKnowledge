package threadObjectClassCommonMethods;

/**
 * 两个线程交替打印0-100的奇偶数
 * 使用 wait 和 notify 实现
 */
public class WaitNotifyPrintOddEvenWait {
    // 拿到锁我们就打印
    // 打印完，就唤醒其他线程，自己休眠
    public static void main(String[] args) {
        new Thread(new TurningRunner(),"偶数").start();
        new Thread(new TurningRunner(),"奇数").start();
    }

    private static int count = 0;
    private static final Object lock = new Object();
    static class TurningRunner implements Runnable{
        @Override
        public void run() {
            while(count <= 100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+count);
                    count++;
                    lock.notify();
                    if(count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
