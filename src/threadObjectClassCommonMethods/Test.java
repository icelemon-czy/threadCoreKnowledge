package threadObjectClassCommonMethods;

public class Test {
    public static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t_even = new Thread(new EvenThread(lock));
        Thread t_odd = new Thread(new OddThread(lock));
        t_even.start();
        Thread.sleep(1);
        t_odd.start();
    }

}
class EvenThread implements Runnable{
    public final Object lock;

    public EvenThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i+=2) {
            synchronized (this.lock){
                System.out.println(i);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
class OddThread implements Runnable{
    public final Object lock;

    public OddThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i+=2) {
            synchronized (this.lock){
                System.out.println(i);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
