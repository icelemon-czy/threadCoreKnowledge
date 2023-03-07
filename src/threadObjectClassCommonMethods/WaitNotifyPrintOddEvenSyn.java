package threadObjectClassCommonMethods;

/**
 * 两个线程交替打印0-100的奇偶数
 * 使用synchronized关键字实现
 */
public class WaitNotifyPrintOddEvenSyn {
    // 新建2个线程，第一个只处理偶数，第二个只处理奇数(位运算)
    // 使用synchronized 来通信

    private static int count = 0;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread evenThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(count < 100){
                            synchronized (lock){
                                if( (count&1) == 0){
                                    System.out.println(Thread.currentThread().getName()+":"+count);
                                    count++;
                                }
                            }
                        }
                    }
                }
        );

        Thread oddThead = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(count < 100){
                            synchronized (lock){
                                if( (count&1) == 1){
                                    System.out.println(Thread.currentThread().getName()+":"+count);
                                    count++;
                                }
                            }
                        }
                    }
                }
        );
        evenThread.start();
        oddThead.start();
    }

}
