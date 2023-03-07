package stopThreads.volatiledemo;

/**
 * 演示用volatile 的局限 part1 看似可行的
 */
public class WrongWayVolatile implements Runnable{
    private volatile boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        try {

            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100倍数");
                }
                num++;
                Thread.sleep(1);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}
