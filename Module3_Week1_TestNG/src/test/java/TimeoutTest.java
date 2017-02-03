import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Date;

public class TimeoutTest {

    private Thread mainThread;
    protected Timeout timeout;

    @BeforeClass(groups = "config")
    public void setUp(){
        timeout = new Timeout();
        System.out.println("BeforeClass run");
    }

    @Test(groups = {"timeout"})
    public void testVerifyTimeoutIs2Sec() throws InterruptedException {
        Date pastDate = new Date(System.currentTimeMillis());
        Timeout.sleep(2);
        Date newDate = new Date(System.currentTimeMillis());
        long seconds = (newDate.getTime() - pastDate.getTime()) / 1000;
        Assert.assertEquals(seconds, 2, "Expected timeout = 2 sec");
    }

    @Test(groups = {"timeout"}, expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "timeout value is negative")
    public void negativeTestThreadSleepForMinus2SecWillThrowException() throws InterruptedException {
        Thread.sleep(-2000L);
        System.out.println("Sleep for -2000 mills");
        Assert.fail("No exception was thrown");
    }

    @Test(groups = {"timeout"}, description = "Sleep doesn't throw InterruptedException after interrupt main thread")
    public void testSleepCatchInterruptedException() throws InterruptedException {
        mainThread = Thread.currentThread();
        (new Thread(new Runnable() {
            public void run() {
                try {
                    Timeout.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        })).start();
        Timeout.sleep(2);
    }

    @AfterClass(groups = "config")
    public void afterClass(){
        timeout = null;
        System.out.println("AfterClass run");
    }
}