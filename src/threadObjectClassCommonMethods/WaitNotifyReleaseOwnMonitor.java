package threadObjectClassCommonMethods;

public class WaitNotifyReleaseOwnMonitor {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("Thread A got Resource A");
                    synchronized (resourceB) {
                        System.out.println("Thread A got Resource B");
                        try {
                            System.out.println("Thread A releases Resource A");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA) {
                    System.out.println("Thread B got resource A lock");
                    System.out.println("Thread B try to get resource B lock");
                    synchronized (resourceB) {
                        System.out.println("Thread B got resource B lock");
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
