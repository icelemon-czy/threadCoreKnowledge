package sixStates;

/**
 * 展示线程的New, Runnable,Terminated 状态,即使是正在运行，也是Runnable 状态，而不是Running
 */
public class newRunnableTerminated implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread= new Thread(new newRunnableTerminated());
        // new
        System.out.println(thread.getState());
        thread.start();
        //Runnable
        System.out.println(thread.getState());
        Thread.sleep(10);
        // 打印出Runnable状态
        System.out.println(thread.getState());
        Thread.sleep(100);
        // 打印出Terminated状态
        System.out.println(thread.getState());
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
