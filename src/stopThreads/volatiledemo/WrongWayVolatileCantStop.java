package stopThreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示用volatile的局限性 part 2
 * 陷入阻塞时，volatile是无法阻止线程
 * 此例子中，生产者生产速度很快，消费者消费速度很慢
 * 所以阻塞队列满了以后，生产者会阻塞
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue stoarge = new ArrayBlockingQueue(10);
        Producer producer = new Producer(stoarge);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer consumer = new Consumer(stoarge);
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+" 被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据了，我们应该让生产者停下
        producer.canceled = true;
        System.out.println(producer.canceled);
    }

}
class Producer implements Runnable{
    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100倍数 被放入仓库中");
                }
                num++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者停止运行");
        }
    }
}

class Consumer{
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if (Math.random()>0.95) {
            return false;
        }
        return true;
    }
}