package threadObjectClassCommonMethods;
// 展示 线程 sleep 的时候不释放 synchronized的monitor，等
// sleep时间到了之后，正常结束之后才释放锁
public class SleepDonotReleaseMonitor implements Runnable {
    public static void main(String[] args) {
        SleepDonotReleaseMonitor r = new SleepDonotReleaseMonitor();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn()  {
        System.out.println("线程"+ Thread.currentThread().getName()+" 获取到了monitor");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+ Thread.currentThread().getName()+" 退出同步代码块儿");
    }
}
