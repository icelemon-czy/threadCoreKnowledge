package startThread;

/**
 *  对比 start 和 run 两种启动线程的方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        // Runnable: 主线程执行
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
        };
      //  runnable.run();
        new Thread(runnable).run();
        //新建子线程
        new Thread(runnable).start();
    }
}
