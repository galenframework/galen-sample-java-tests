package net.mindengine.galen.java.sample.components;

import net.mindengine.galen.api.Galen;
import net.mindengine.galen.java.sample.components.GalenReportsContainer;
import net.mindengine.galen.reports.TestReport;
import net.mindengine.galen.reports.model.LayoutReport;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

public abstract class GalenTestBase {

    private static final String ENV_URL = "http://testapp.galenframework.com";
    protected WebDriver driver;

    public void checkLayout(WebDriver driver, String specPath, List<String> includedTags) throws IOException {
        String title = "Check layout " + specPath;
        LayoutReport layoutReport = Galen.checkLayout(driver, specPath, includedTags, null, new Properties(), null);
        report.get().layout(layoutReport, title);

        if (layoutReport.errors() > 0) {
            throw new RuntimeException("Incorrect layout: " + title);
        }
    }

    ThreadLocal<TestReport> report = new ThreadLocal<TestReport>();

    @BeforeMethod
    public void initReport(Method method) {
        report.set(GalenReportsContainer.get().registerTest(method));
    }

    @BeforeMethod
    public void createDriver(Object [] args) {
        driver = new FirefoxDriver();

        if (args.length > 0) {
            if (args[0] != null && args[0] instanceof TestDevice) {
                TestDevice device = (TestDevice)args[0];
                if (device.getScreenSize() != null) {
                    driver.manage().window().setSize(device.getScreenSize());
                }
            }
        }
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }


    public void load(String uri) {
        driver.get(ENV_URL + uri);
    }

    @DataProvider(name = "devices")
    public Object [][] devices () {
        return new Object[][] {
                {new TestDevice("mobile", new Dimension(450, 800), asList("mobile"))},
                {new TestDevice("tablet", new Dimension(750, 800), asList("tablet"))},
                {new TestDevice("desktop", new Dimension(1024, 800), asList("desktop"))}
        };
    }




    public static class TestDevice {
        private final String name;
        private final Dimension screenSize;
        private final List<String> tags;

        public TestDevice(String name, Dimension screenSize, List<String> tags) {
            this.name = name;
            this.screenSize = screenSize;
            this.tags = tags;
        }


        public String getName() {
            return name;
        }

        public Dimension getScreenSize() {
            return screenSize;
        }

        public List<String> getTags() {
            return tags;
        }
    }
}
