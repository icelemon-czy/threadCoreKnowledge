package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CinemaReadWrite {
    public static void main(String[] args) {
        new Thread(()-> write(),"Thread 1").start();
        new Thread(()-> read(),"Thread 2").start();
        new Thread(()-> read(),"Thread 3").start();
        new Thread(()-> write(),"Thread 4").start();
        new Thread(()-> read(),"Thread 5").start();
    }
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readlock = lock.readLock();;
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private static int x = 0;
    private  static  int y = 0;

    private static void read(){
        readlock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 得到读锁，正在读取");
 //           System.out.println("X : "+x);
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+" 释放读锁");
            readlock.unlock();
        }
    }

    private static void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 得到写锁，正在写入");
 //           y++;
   //         System.out.println("Y : "+y);
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+" 释放写锁");
            writeLock.unlock();
        }
    }
}
