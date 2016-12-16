package page.part;

import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 16.12.2016.
 */
public class AddContentSettingsPopUp {

    private static final By TITLEINPUT = get("AddContentPopUp.TitleInput");
    private static final By IMAGEINPUT = get("AddContentPopUp.ImageInput");
    private static final By SHORTTITLEINPUT = get("AddContentPopUp.ShortTitleInput");
    private static final By SHORTSUMMARYINPUT = get("AddContentPopUp.ShortSummaryInput");
    private static final By SUMMARYINPUT = get("AddContentPopUp.SummaryInput");
    private static final By CALLTOACTIONINPUT = get("AddContentPopUp.CallToActionInput");
    private static final By LOGOINPUT = get("AddContentPopUp.LogoInput");
    private static final By SUBMITBUTTON = get("AddContentPopUp.SubmitButton");
    private static final By UPDATEBUTTON = get("AddContentPopUp.UpdateButton");
    private static final By IMAGE = get("AddContentPopUp.Image");
    private static final By LOGO = get("AddContentPopUp.Logo");
    private static final By SUCCESSFULADUPDATENOTIF = get("AddContentPopUp.SuccessfulAdUpdateNotif");
    private static final int TITLESTRINGLENGTH = 15;
    private static final int SHORTTITLESTRINGLENGTH = 50;
    private static final int SHORTSUMMARYSTRINGLENGTH = 60;
    private static final int SUMMARYSTRINGLENGTH = 100;
    private static final int CALLTOACTIONSTRINGLENGTH = 20;
    private static final String IMAGEPATH = String.format("%s\\src\\main\\resources\\Koala.jpg", System.getProperty("user.dir"));
    private static final String LOGOPATH = String.format("%s\\src\\main\\resources\\Jellyfish.jpg", System.getProperty("user.dir"));
    private String adTitle;
    private WebDriver driver;

    public AddContentSettingsPopUp(WebDriver driver) {
        this.driver = driver;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void addAdTitle() {
        adTitle = RandomizersUtils.randomText(TITLESTRINGLENGTH);
        driver.findElement(TITLEINPUT).clear();
        driver.findElement(TITLEINPUT).sendKeys(adTitle);
    }

    public void addAdImage() {
        driver.findElement(IMAGEINPUT).clear();
        driver.findElement(IMAGEINPUT).sendKeys(IMAGEPATH);
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(IMAGE));
    }

    public void addAdShortTitle() {
        driver.findElement(SHORTTITLEINPUT).clear();
        driver.findElement(SHORTTITLEINPUT).sendKeys(RandomizersUtils.randomText(SHORTTITLESTRINGLENGTH));
    }

    public void addAdShortSummary() {
        driver.findElement(SHORTSUMMARYINPUT).clear();
        driver.findElement(SHORTSUMMARYINPUT).sendKeys(RandomizersUtils.randomText(SHORTSUMMARYSTRINGLENGTH));
    }

    public void addAdSummary() {
        driver.findElement(SUMMARYINPUT).clear();
        driver.findElement(SUMMARYINPUT).sendKeys(RandomizersUtils.randomText(SUMMARYSTRINGLENGTH));
    }

    public void addAdCallToAction() {
        driver.findElement(CALLTOACTIONINPUT).clear();
        driver.findElement(CALLTOACTIONINPUT).sendKeys(RandomizersUtils.randomText(CALLTOACTIONSTRINGLENGTH));
    }

    public void addAdLogo() {
        driver.findElement(LOGOINPUT).clear();
        driver.findElement(LOGOINPUT).sendKeys(LOGOPATH);
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(LOGO));
    }

    public void saveNewAdSettings() {
        driver.findElement(SUBMITBUTTON).click();
    }

    public void saveSpecifiedAdSettings() {
        driver.findElement(UPDATEBUTTON).click();
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULADUPDATENOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULADUPDATENOTIF));
    }
}
