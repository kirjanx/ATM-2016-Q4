public class Timeout {
    public Timeout() {
    }

    public static void sleep(int sec) throws InterruptedException {
        try {
            Thread.sleep((long)(sec * 1000));
        } catch (InterruptedException e) {
            //System.err.println("An InterruptedException was caught: " + e.getMessage());
        }

    }
}