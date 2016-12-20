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
    private static final Properties CREDENTIALS;
    private static final String LOGINPROPERTYNAME = "login";
    private static final String PASSWORDPROPERTYNAME = "password";
    private WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    static {
        CREDENTIALS = PropertiesUtils.getProperties("/loginCredentials.properties");
    }

    public WidgetReportPage logIn() {
        driver.findElement(LOGININPUT).sendKeys(CREDENTIALS.getProperty(LOGINPROPERTYNAME));
        driver.findElement(PASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(LOGINBUTTON).click();
        return new WidgetReportPage(driver);
    }

    public void logInWithNewPassword(String newPassword) {
        driver.findElement(LOGININPUT).sendKeys(CREDENTIALS.getProperty(LOGINPROPERTYNAME));
        driver.findElement(PASSWORDINPUT).sendKeys(newPassword);
        driver.findElement(LOGINBUTTON).click();
    }

    public boolean isLogInButtonDisplayed() {
        return driver.findElement(LOGINBUTTON).isDisplayed();
    }
}
