package page.settings;

import base.PageBase;
import helper.PropertiesUtils;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class GeneralInfoPage extends PageBase {

    private static final String PAGETITLE = "General Info";
    private static final By CHANGEEMAILBUTTON = get("GeneralInfoPage.ChangeEmailButton");
    private static final By CHANGEPASSWORDBUTTON = get("GeneralInfoPage.ChangePasswordButton");
    private static final By PUBLISHERCHECKBOX = get("GeneralInfoPage.PublisherCheckbox");
    private static final By ADVERTISERCHECKBOX = get("GeneralInfoPage.AdvertiserCheckbox");
    private static final By CHANGEEMAILINPUT = get("GeneralInfoPage.ChangeEmailNewEmailInput");
    private static final By CHANGEEMAILPASSWORDINPUT = get("GeneralInfoPage.ChangeEmailPasswordInput");
    private static final By EMAILFIELD = get("GeneralInfoPage.EmailField");
    private static final By CHANGEEMAILSAVEBUTTON = get("GeneralInfoPage.ChangeEmailSaveButton");
    private static final By CHANGEPASSWORDSAVEBUTTON = get("GeneralInfoPage.ChangePasswordSaveButton");
    private static final By OLDPASSWORDINPUT = get("GeneralInfoPage.OldPasswordInput");
    private static final By NEWPASSWORDINPUT = get("GeneralInfoPage.NewPasswordInput");
    private static final By RETYPEPASSWORDINPUT = get("GeneralInfoPage.RetypePasswordInput");
    private static final By SAVEBUTTON = get("GeneralInfoPage.SaveButton");
    private static final By SUCCESSFULEMAILCHANGENOTIF = get("GeneralInfoPage.SuccessfulEmailChangeNotif");
    private static final By SUCCESSFULPASSWORDCHANGENOTIF = get("GeneralInfoPage.SuccessfulPasswordChangeNotif");
    private static final By SUCCESSFULACCOUNTTYPECHANGENOTIF = get("GeneralInfoPage.SuccessfulAccountTypeChangeNotif");
    private static final Properties CREDENTIALS;
    private static final String emailMask = "testemail%s@test.com";
    private static final int NEWPASSWORDLENGTH = 10;
    private static final String LOGINPROPERTYNAME = "login";
    private static final String PASSWORDPROPERTYNAME = "password";
    private String newEmail;
    private String newPassword;

    public GeneralInfoPage(WebDriver driver) {
        super(driver);
    }

    static {
        CREDENTIALS = PropertiesUtils.getProperties("/loginCredentials.properties");
    }

    public String getNewPassword() {
        return newPassword;
    }

    public boolean isGeneralInfoPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }

    public void changeEmail() {
        driver.findElement(CHANGEEMAILBUTTON).click();
        newEmail = String.format(emailMask, RandomizersUtils.randomPrefix());
        driver.findElement(CHANGEEMAILINPUT).sendKeys(newEmail);
        driver.findElement(CHANGEEMAILPASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(CHANGEEMAILSAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULEMAILCHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULEMAILCHANGENOTIF));
    }

    public void changePassword() {
        driver.findElement(CHANGEPASSWORDBUTTON).click();
        newPassword = RandomizersUtils.randomPassword(NEWPASSWORDLENGTH);
        driver.findElement(OLDPASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(NEWPASSWORDINPUT).sendKeys(newPassword);
        driver.findElement(RETYPEPASSWORDINPUT).sendKeys(newPassword);
        driver.findElement(CHANGEPASSWORDSAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULPASSWORDCHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULPASSWORDCHANGENOTIF));
        driver.navigate().refresh();
    }

    public void addRemoveAdvertiserFunctions() {
        driver.findElement(ADVERTISERCHECKBOX).click();
        driver.findElement(SAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULACCOUNTTYPECHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULACCOUNTTYPECHANGENOTIF));
    }

    public void addRemovePublisherFunctions() {
        driver.findElement(PUBLISHERCHECKBOX).click();
        driver.findElement(SAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULACCOUNTTYPECHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULACCOUNTTYPECHANGENOTIF));
    }

    public void setDefaultEmail() {
        driver.findElement(CHANGEEMAILBUTTON).click();
        driver.findElement(CHANGEEMAILINPUT).sendKeys(CREDENTIALS.getProperty(LOGINPROPERTYNAME));
        driver.findElement(CHANGEEMAILPASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(CHANGEEMAILSAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULEMAILCHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULEMAILCHANGENOTIF));
    }

    public void setDefaultPassword() {
        driver.findElement(CHANGEPASSWORDBUTTON).click();
        driver.findElement(OLDPASSWORDINPUT).sendKeys(newPassword);
        driver.findElement(NEWPASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(RETYPEPASSWORDINPUT).sendKeys(CREDENTIALS.getProperty(PASSWORDPROPERTYNAME));
        driver.findElement(CHANGEPASSWORDSAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULPASSWORDCHANGENOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULPASSWORDCHANGENOTIF));
        driver.navigate().refresh();
    }

    public boolean isEmailChanged() {
        return driver.findElement(EMAILFIELD).getText().contentEquals(newEmail);
    }

    public boolean isEmailDefault() {
        return driver.findElement(EMAILFIELD).getText().contentEquals(CREDENTIALS.getProperty(LOGINPROPERTYNAME));
    }
}
