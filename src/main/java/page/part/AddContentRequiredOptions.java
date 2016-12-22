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
public class AddContentRequiredOptions {

    private static final By IMAGEINPUT = get("AddContentRequiredOptions.ImageInput");
    private static final By SUBMITBUTTON = get("AddContentRequiredOptions.SubmitButton");
    private static final By UPDATEBUTTON = get("AddContentRequiredOptions.UpdateButton");
    private static final By IMAGE = get("AddContentRequiredOptions.Image");
    private static final By SUCCESSFULADUPDATENOTIF = get("AddContentRequiredOptions.SuccessfulAdUpdateNotif");
    private static final int TITLESTRINGLENGTH = 15;
    private static final String IMAGEPATH = String.format("%s\\src\\main\\resources\\Koala.jpg", System.getProperty("user.dir"));
    private String adTitle;
    private WebDriver driver;

    public AddContentRequiredOptions(WebDriver driver) {
        this.driver = driver;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void addAdTitle(By titleInput) {
        adTitle = RandomizersUtils.randomText(TITLESTRINGLENGTH);
        driver.findElement(titleInput).clear();
        driver.findElement(titleInput).sendKeys(adTitle);
    }

    public void addAdImage() {
        driver.findElement(IMAGEINPUT).clear();
        driver.findElement(IMAGEINPUT).sendKeys(IMAGEPATH);
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(IMAGE));
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
