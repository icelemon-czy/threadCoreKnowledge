package threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 10 个线程分别打印生日信息
 */
public class ThreadLocalNormalUsage01 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }

    }
    public String date(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT 计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
