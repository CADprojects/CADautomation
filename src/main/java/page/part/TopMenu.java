package page.part;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.ContactPage;
import page.HelpHomePage;
import page.LogInPage;
import page.settings.GeneralInfoPage;

import static helper.Locators.get;

public class TopMenu {

    private static final By FAQLINK = get("TopMenu.FaqLink");
    private static final By CONTACTLINK = get("TopMenu.ContactLink");
    private static final By SETTINGSLINK = get("TopMenu.AccountSettingsLink");
    private static final By LOGOUTLINK = get("TopMenu.LogOutLink");
    private static final By USERBLOCK = get("TopMenu.UserBlock");
    private WebDriver driver;

    public TopMenu (WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHelpHomePage() {
        driver.findElement(FAQLINK).click();
    }

    public void navigateToContactPage() {
        driver.findElement(CONTACTLINK).click();
    }

    public void navigateToGeneralInfoPage() {
        driver.findElement(USERBLOCK).click();
        driver.findElement(SETTINGSLINK).click();
    }

    public void navigateToLoginPage() {
        driver.findElement(USERBLOCK).click();
        driver.findElement(LOGOUTLINK).click();
    }

    public boolean isLogOutLinkDisplayed() {
        driver.findElement(USERBLOCK).click();
        return driver.findElement(LOGOUTLINK).isDisplayed();
    }


}
