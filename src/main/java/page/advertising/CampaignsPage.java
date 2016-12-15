package page.advertising;

import base.PageBase;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class CampaignsPage extends PageBase {

    private static final By NEWCAMPAIGNBUTTON = get("CampaignsPage.NewCampaignButton");
    private static final By CONFIRMDELETIONBUTTON = get("CampaignsPage.ConfirmCampaignDeletionButton");
    private static final By SUCCESSFULDELETIONNOTIF = get("CampaignsPage.SuccessfulCampaignDeletionNotif");
    private static final String SPECIFIEDCAMPAIGNCONTAINER = "//div[@class='campaignInfo']/div[1][text()='%s']/..";
    private static final String SPECIFIEDCAMPAIGNDELETEBUTTON = "//div[@class='campaignInfo']/div[1][text()='%s']/..//a[@id='lbDelete']";
    private static final String SPECIFIEDCAMPAIGNEDITBUTTON = "//div[@class='campaignInfo']/div[1][text()='%s']/..//a[@id='lbEdit']";
    private String campaignID;

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    public CampaignsPage(WebDriver driver, String campaignID) {
        super(driver);
        this.campaignID = campaignID;
    }

    public CampaignSettingsPage startCampaignCreating() {
        driver.findElement(NEWCAMPAIGNBUTTON).click();
        return new CampaignSettingsPage(driver);
    }

    public boolean isSpecifiedCampaignDisplayed() {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNCONTAINER, campaignID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedCampaign() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNDELETEBUTTON, campaignID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't created");
        }
        driver.findElement(CONFIRMDELETIONBUTTON).click();
    }
}
