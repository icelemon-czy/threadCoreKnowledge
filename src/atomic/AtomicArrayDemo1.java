package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 演示原子数组的使用方法
 */
public class AtomicArrayDemo1 {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];
        Runnable incrementer = new Incrementer(atomicIntegerArray);
        Runnable decrementer = new Decrementer(atomicIntegerArray);
        for (int i = 0; i < 100; i++) {
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i] = new Thread(decrementer);
            threadsIncrementer[i].start();
            threadsDecrementer[i].start();
        }
        for (int i = 0; i < 100; i++) {
            threadsIncrementer[i].join();
            threadsDecrementer[i].join();
        }
        for (int i = 0; i < 100; i++) {
            if(atomicIntegerArray.get(i) != 0){
                System.out.println("not equal to 0");
            }
        }
        System.out.println("运行结束");
    }
}
class Decrementer implements Runnable{
    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{
    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}