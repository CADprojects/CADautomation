package page;

import helper.PropertiesUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.reporting.WidgetReportPage;

import java.util.Properties;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class LogInPage {

    private static final By LOGININPUT = get("LogInPage.UserNameInput");
    private static final By PASSWORDINPUT = get("LogInPage.PasswordInput");
    private static final By LOGINBUTTON = get("LogInPage.LogInButton");
    private static final Properties credentials;
    protected WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    static {
        credentials = PropertiesUtils.getProperties("/loginCredentials.properties");
    }

    public WidgetReportPage logIn() {
        driver.findElement(LOGININPUT).sendKeys(credentials.getProperty("login"));
        driver.findElement(PASSWORDINPUT).sendKeys(credentials.getProperty("password"));
        driver.findElement(LOGINBUTTON).click();
        return new WidgetReportPage(driver);
    }

    public boolean isLogInButtonDisplayed() {
        return driver.findElement(LOGINBUTTON).isDisplayed();
    }
}
