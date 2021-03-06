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

    public void deleteSpecifiedSource(String campaignID, String sourceDeleteButton, By notification) {
        try {
            driver.findElement(By.xpath(String.format(sourceDeleteButton, campaignID))).click();
            driver.findElement(CONFIRMDELETIONBUTTON).click();
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(notification));
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(notification));
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
