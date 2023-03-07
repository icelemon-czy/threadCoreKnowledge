package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示在获取锁的过程中，中途被打断了
 */
public class LockInterruptibly implements Runnable {
    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread0 = new Thread(lockInterruptibly);
        Thread thread1 = new Thread(lockInterruptibly);
        thread0.start();
        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 尝试获取锁");
        try {
            lock.lockInterruptibly();
            // 获取锁成功
            try {
                System.out.println(name + " 获取锁成功");
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                System.out.println(name + " 睡眠期间，锁被打断");
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(name + " 释放锁");
            }
        } catch (InterruptedException e) {
            // 获取锁时候被中断
            System.out.println(name + " 获取锁被打断");
            e.printStackTrace();
        }
    }
}
