package threadObjectClassCommonMethods;

/**
 * 先join 再mainThread.gatState
 * 通过debugger 看线程join前后的状态对比
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("Thread 0 运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        System.out.println("等待子线程运行完毕");
        t1.join();
        System.out.println("子线程运行完毕");
    }
}
