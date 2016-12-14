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

    public HelpHomePage navigateToHelpHomePage() {
        driver.findElement(FAQLINK).click();
        return new HelpHomePage(driver);
    }

    public ContactPage navigateToContactPage() {
        driver.findElement(CONTACTLINK).click();
        return new ContactPage(driver);
    }

    public GeneralInfoPage navigateToGeneralInfoPage() {
        driver.findElement(USERBLOCK).click();
        driver.findElement(SETTINGSLINK).click();
        return new GeneralInfoPage(driver);
    }

    public LogInPage navigateToLoginPage() {
        driver.findElement(USERBLOCK).click();
        driver.findElement(LOGOUTLINK).click();
        return new LogInPage(driver);
    }

    public boolean isLogOutLinkDisplayed() {
        driver.findElement(USERBLOCK).click();
        return driver.findElement(LOGOUTLINK).isDisplayed();
    }


}
