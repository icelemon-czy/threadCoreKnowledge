package startThread;
// 演示不能两次调用start 方法
public class CannotStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
