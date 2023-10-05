package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Marker;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class Base {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();
    public static ExtentReports reports;
    public static ExtentSparkReporter sparkReporter;
    static WebDriver webDriver;
    public static WebDriverWait wait;
    public static WebDriver getDriver() {
        return driver.get();
    }
    public void createTest() {
        String name = Arrays.asList(getClass().getName().split("\\.")).get(1);
        ExtentTest tests = reports.createTest(name);
        test.set(tests);
    }
    @BeforeSuite
    public void setReportUp() {
        reports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/extentReport.html");
        sparkReporter.config().setReportName("Test");
        sparkReporter.config().setDocumentTitle("Book Cart Test");
        reports.attachReporter(sparkReporter);
    }

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        createTest();
        if(browser.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-dev-shm-usage");
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//            webDriver = new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName(browser);
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
//            webDriver = new FirefoxDriver();
        }
        else {
            throw new Exception("Browser not recognized");
        }
        driver.set(webDriver);
        Duration time = Duration.ofMillis(3000);
        wait = new WebDriverWait(webDriver, time);
        getDriver().get("https://bookcart.azurewebsites.net/");
    }


    @BeforeMethod
    public void createNodeTest(Method method) {
        childTest.set(test.get().createNode(method.getName()));
    }
    @AfterMethod
    public void setTestResult(ITestResult result) {
        String details = "<details><summary>stack trace</summary>" +
                result.getThrowable() +
                "</details>";
        if(ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            String base64 = screenshot.getScreenshotAs(OutputType.BASE64);
            childTest.get().fail(result.getName() + " test failed");
            childTest.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            childTest.get().info(details);
        }
        else if(ITestResult.SUCCESS == result.getStatus()) {
            childTest.get().pass(result.getName() + " test passed");
        }
        else if(ITestResult.SKIP == result.getStatus()) {
            childTest.get().skip(result.getName() + " test skipped");
        }
    }
    @AfterClass
    public static void tearDown() {
        reports.flush();
        getDriver().quit();
    }
}
