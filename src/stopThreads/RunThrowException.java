package stopThreads;

/**
 *  run 无法抛出checked Exception,只能调用 try catch
 */
public class RunThrowException {
    public void aVoid() throws Exception{
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
