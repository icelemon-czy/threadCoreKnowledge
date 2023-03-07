package threadObjectClassCommonMethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程每隔一秒输出当前时间，被中断，观察
 * 1. Thread.sleep
 * 2. TimeUnit.SECONDS.sleep
 */
public class SleepInterrupted implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了");
                e.printStackTrace();
            }
        }
    }
}
