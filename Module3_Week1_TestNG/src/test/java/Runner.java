import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {

        TestNG testNG = new TestNG();
        testNG.addListener(new TestListenerAdapter());
        testNG.addListener(new TestListener());

        final XmlSuite xmlSuiteCalculator = new XmlSuite();

        xmlSuiteCalculator.setName("Calculator and Timeout tests suite");

        xmlSuiteCalculator.setSuiteFiles(new ArrayList <String>(){{
            add("./src/test/resources/suites/calculator.xml");
            add("./src/test/resources/suites/timeout.xml");
        }});

        testNG.setXmlSuites(new ArrayList <XmlSuite>(){{
            add(xmlSuiteCalculator);
        }});

        testNG.run();
    }
}
