package stopThreads;

/**
 * 错误的停止方法:用stop来停止线程会导致线程运行到一半突然停止,没办法完成一个基本单位的操作,会造成脏数据
 * 案例: 连队发装备
 */
public class stopThread implements Runnable {
    @Override
    public void run() {
        // 模拟指挥军队，一共有五个连队,每个连队100人，以连队为单位，发放但要，叫到号的士兵去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("连队"+i+"开始领取武器");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"已经领取完毕");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new stopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
