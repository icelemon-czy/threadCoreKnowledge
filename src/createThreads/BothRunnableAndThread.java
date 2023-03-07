package createThreads;

/**
 * 同时使用 Runnable 和 Thread 两种方式实现线程
 */
public class BothRunnableAndThread {
    public static void main(String[] args) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("我来自Rrnnable");
                    }
                }
        ){
            @Override
            public void run(){
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
