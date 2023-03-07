package stopThreads;

/**
 * 最佳实践2: 在 catch 语句中 调用Thread.currentThread().interrupt() 来
 * 恢复设置中断状态,以便于在后续的执行中依然能够检查到刚才发生到中断
 */
public class RightWayStopThreadInProduct2 implements Runnable{

    @Override
    public void run() {
        while(true) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted,程序运行结束");
                break;
            }
            reInterruptMethod();
        }
    }

    private void reInterruptMethod() {
        try {
            System.out.println("go");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
