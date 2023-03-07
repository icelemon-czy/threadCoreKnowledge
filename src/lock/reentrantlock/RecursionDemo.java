package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {
    public static void main(String[] args) {
        accessResource();
    }
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource(){
        lock.lock();
        try {
            System.out.println("已经对资源进行处理");
            if(lock.getHoldCount() < 5){
                System.out.println(lock.getHoldCount());
                accessResource();
            }
        }finally {
            lock.unlock();
        }
    }
}
