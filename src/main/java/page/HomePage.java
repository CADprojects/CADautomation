package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class HomePage {

    private static final By LOGINBUTTON = get("HomePage.LogInButton");
    private static final By SIGNUPBUTTON = get("HomePage.SignUpButton");
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public SignUpPage navigateToSignUpPage() {
        driver.findElement(SIGNUPBUTTON).click();
        return new SignUpPage(driver);
    }

    public LogInPage navigateToLoginPage() {
        driver.findElement(LOGINBUTTON).click();
        return new LogInPage(driver);
    }
}
