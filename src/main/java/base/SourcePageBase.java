package base;

import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.advertising.CampaignSettingsPage;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 21.12.2016.
 */
public class SourcePageBase extends PageBase {

    private static final By CONFIRMDELETIONBUTTON = get("SourcePageBase.ConfirmSourceDeletionButton");
    private static final By SUCCESSFULDELETIONNOTIF = get("SourcePageBase.SuccessfulSourceDeletionNotif");

    public SourcePageBase(WebDriver driver) {
        super(driver);
    }

    public void startSourceCreation(By newSourceButton) {
        driver.findElement(newSourceButton).click();
    }

    public boolean isSpecifiedSourceDisplayed(String campaignID, String sourceContainer) {
        try {
            return driver.findElement(By.xpath(String.format(sourceContainer, campaignID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedSource(String campaignID, String sourceDeleteButton) {
        try {
            driver.findElement(By.xpath(String.format(sourceDeleteButton, campaignID))).click();
            driver.findElement(CONFIRMDELETIONBUTTON).click();
            WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
            WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }

    public void openSpecifiedSourceSettings(String campaignID, String sourceEditButton) {
        try {
            driver.findElement(By.xpath(String.format(sourceEditButton, campaignID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }
}
