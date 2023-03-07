package threadObjectClassCommonMethods;

/**
 * 3个线程， 1 和2 首先被阻塞，线程3唤醒他们，
 * 对比notify 和 notify all 区别
 * start 先执行不代表 线程先启动
 */
public class WaitNotifyAll implements Runnable{
    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+" got Resource A lock.");
            try {
                System.out.println(Thread.currentThread().getName()+" waits to start ");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+" is waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        threadA.start();
        Thread threadB = new Thread(r);
        threadB.start();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    resourceA.notifyAll();
                    System.out.println("Thread C notified");
                }
            }
        }).start();
    }
}
