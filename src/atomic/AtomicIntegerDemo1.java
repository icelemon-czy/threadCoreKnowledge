package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  演示AtomicInteger的基本用法，对比非原子类的线程安全问题
 *  使用了原子类之后，不需要加锁，也可以保证线程安全
 */
public class AtomicIntegerDemo1 implements Runnable{
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic(){
        atomicInteger.getAndIncrement();
    }

    private static volatile  int basicCount = 0;
    public synchronized void incrementBasic(){
        basicCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类结果: "+atomicInteger.get());
        System.out.println("基本类结果: "+basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}
