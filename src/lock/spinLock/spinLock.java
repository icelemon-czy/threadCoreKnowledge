package lock.spinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用Atomic 实现自旋锁
 */
public class spinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign.compareAndSet(null,current)){
            System.out.println("自旋获取失败，再次尝试");
        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }

    public static void main(String[] args) {
        spinLock lock = new spinLock();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName+"开始尝试获取自旋锁");
                lock.lock();;
                System.out.println(threadName+"获取到自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.out.println(threadName+"释放了到自旋锁");
                }
            }
        };
        Thread thread1 =new Thread( runnable);
        Thread thread2 =new Thread( runnable);
        thread1.start();
        thread2.start();
    }
}
