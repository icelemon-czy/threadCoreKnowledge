package threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000 个线程分别打印生日信息,用线程池执行
 * 利用ThreadLocal 给每个线程分配dateFormat对象，保证了线程安全，高效利用了内存
 */
public class ThreadLocalNormalUsage05 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }
    public String date(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT 计时
        Date date = new Date(1000 * seconds);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}
