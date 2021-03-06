package base;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LogInPage;
import page.reporting.WidgetReportPage;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static final String URL = "http://dev1.content.ad";
    protected LogInPage logInPage;
    protected WidgetReportPage widgetReportPage;
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
//        String browser = System.getProperty("browser").toLowerCase();
//        switch (browser) {
//            case "chrome":
//
//                break;
//
//            default:
//                driver = new FirefoxDriver();
//                break;
//        }
        driver = new FirefoxDriver(); // change to chromedriver to execute tests in chrome
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        logInPage = new LogInPage(driver);
        widgetReportPage = logInPage.logIn();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
