package createThreads;

/**
 * 用 Thread 方式实现线程
 */
public class ThreadStyle extends Thread {
    @Override
    public void run(){
        System.out.println("Thread 类创建线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
