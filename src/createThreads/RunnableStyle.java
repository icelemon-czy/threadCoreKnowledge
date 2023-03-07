package createThreads;

/**
 * 用 Runnable 方式创建线程
 */
public class RunnableStyle implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Runnable 方法实现线程");
    }
}
