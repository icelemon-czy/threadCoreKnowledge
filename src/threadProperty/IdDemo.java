package threadProperty;

/**
 *  ID 从1开始，JVM运行起来后，我们自己创建的线程ID不是0
 */
public class IdDemo {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程ID"+ Thread.currentThread().getId());
        System.out.println("子线程ID"+ thread.getId());
    }
}
