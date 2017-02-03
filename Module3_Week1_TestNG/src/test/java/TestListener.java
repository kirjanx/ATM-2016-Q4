import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestListener implements ISuiteListener {
    public void onStart(ISuite suite) {
        System.out.println("Suite started: " + suite.getName());
    }

    public void onFinish(ISuite suite) {
        System.out.println("Suite finished: " + suite.getName());
    }
}
