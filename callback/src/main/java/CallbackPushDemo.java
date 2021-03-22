public class CallbackPushDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(" Main thread is running!");

        Runnable r = new Runnable() {
            public void run() {
                new CallbackPushDemo().runningAsync(new CallbackWithPush() {
                    public void pushComplete() {
                        System.out.println("Callback done");
                    }

                    public void pushData(String data) {
                        System.out.println("Callback data: " + data);
                    }

                    public void pushError(Exception ex) {
                        System.out.println("Callback error, got a exception: " + ex);
                    }
                });
            }
        };

        Thread t = new Thread(r);
        t.start();

        Thread.sleep(2000);
        System.out.println("Main thread completed!");
    }

    public void runningAsync(CallbackWithPush callback) {
        System.out.println("I am running on a separate thread.");
        sleep(1000);
        callback.pushData("Data1");
        callback.pushData("Data2");
        callback.pushData("Data3");

        callback.pushError(new RuntimeException("Ups"));
        callback.pushComplete();
    }

    private void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
