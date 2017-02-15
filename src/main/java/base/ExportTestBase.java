package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.HomePage;
import page.LogInPage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportTestBase extends TestBase {

    protected static final String FOLDERFOREXPORT = "d:\\contentad\\folder4export\\";
    private static String browser;

    @BeforeMethod(alwaysRun = true)
    public void clearFolderForExport() throws IOException {
        File file = new File(FOLDERFOREXPORT);
        FileUtils.cleanDirectory(file);
    }

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
//        browser = System.getProperty("browser").toLowerCase();
//        switch (browser) {
//            case "chrome":
//                Map<String, Object> prefs = new HashMap<>();
//                prefs.put("download.default_directory", FOLDERFOREXPORT);
//                ChromeOptions options = new ChromeOptions();
//                options.setExperimentalOption("prefs", prefs);
//                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//                driver = new ChromeDriver(capabilities);
//                break;
//            default:
//                FirefoxProfile f = new FirefoxProfile();
//                f.setPreference("browser.download.folderList", 2);
//                f.setPreference("browser.download.dir", FOLDERFOREXPORT);
//                f.setPreference("browser.helperApps.alwaysAsk.force", false);
//                f.setPreference("browser.download.manager.showWhenStarting", false);
//                f.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
//                driver = new FirefoxDriver(f);
//                break;
//        }
        FirefoxProfile f = new FirefoxProfile();
        f.setPreference("browser.download.folderList", 2);
        f.setPreference("browser.download.dir", FOLDERFOREXPORT);
        f.setPreference("browser.helperApps.alwaysAsk.force", false);
        f.setPreference("browser.download.manager.showWhenStarting", false);
        f.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
        driver = new FirefoxDriver(f); // change to chromedriver to execute tests in chrome
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("download.default_directory", FOLDERFOREXPORT);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", prefs);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        logInPage = new LogInPage(driver);
        widgetReportPage = logInPage.logIn();
    }
}
