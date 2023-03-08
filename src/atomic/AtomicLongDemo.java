package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 演示高并发场景下， LongAdder 比 AtomicLong 性能好
 */
public class AtomicLongDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while(!service.isTerminated()){

        }
        long end = System.currentTimeMillis();
        System.out.println(counter.get());
        System.out.println("AtomicLong 耗时 "+(end- start));
//        Thread.sleep(10000);
//        System.out.println(counter.get());
    }

    private static class Task implements Runnable{
        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        private AtomicLong counter ;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
