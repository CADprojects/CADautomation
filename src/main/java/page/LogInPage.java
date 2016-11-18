package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.reporting.WidgetReportPage;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class LogInPage {

    private static final By LOGININPUT = get("LogInPage.UserNameInput");
    private static final By PASSWORDINPUT = get("LogInPage.PasswordInput");
    private static final By LOGINBUTTON = get("LogInPage.LogInButton");
    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "BRStest!*";
    protected WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public WidgetReportPage logIn() {
        driver.findElement(LOGININPUT).sendKeys(EMAIL);
        driver.findElement(PASSWORDINPUT).sendKeys(PASSWORD);
        driver.findElement(LOGINBUTTON).click();
        return new WidgetReportPage(driver);
    }

    public boolean isLogInButtonDisplayed() {
        return driver.findElement(LOGINBUTTON).isDisplayed();
    }
}
