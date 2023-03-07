package threadObjectClassCommonMethods;

/**
 * 演示 join被打断
 */
public class JoinInterruptDemo {
    public static void main(String[] args) {
        Thread mainThread=  Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread 1 finished" );
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程完成完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" 主线程被中断");
            thread1.interrupt();
            e.printStackTrace();
        }
        System.out.println("子线程已经运行完毕");
    }
}
