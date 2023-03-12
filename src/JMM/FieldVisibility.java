package JMM;

/**
 * 演示可见性带来的问题
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    private void change(){
        a=3;
        b=a;
    }
    private void print(){
        System.out.println("b: "+ b+" a:"+ a);
    }

    public static void main(String[] args) {
        FieldVisibility test = new FieldVisibility();
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    test.change();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    test.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        one.start();
        two.start();

    }
}
