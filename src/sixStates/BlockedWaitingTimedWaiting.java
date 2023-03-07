package sixStates;

public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        // 打印出Timed_waiting, 因为正在执行Thread.sleeping 1000
        System.out.println(thread1.getState());
        // 打印出Blocked 状态，因为Thread2 想拿到sync()的锁
        System.out.println(thread2.getState());
        Thread.sleep(1300);
        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
