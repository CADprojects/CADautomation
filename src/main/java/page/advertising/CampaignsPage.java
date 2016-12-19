package page.advertising;

import base.PageBase;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

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
    private static final String SPECIFIEDCAMPAIGNCPCBLOCKROWS = "//div[@class='campaignInfo']/div[1][text()='%s']/../div[@class='campaignCPC']/div";
    private String campaignID;

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    public CampaignsPage(WebDriver driver, String campaignID) {
        super(driver);
        this.campaignID = campaignID;
    }

    public String getCampaignID() {
        return campaignID;
    }

    public CampaignSettingsPage startCampaignCreating() {
        driver.findElement(NEWCAMPAIGNBUTTON).click();
        return new CampaignSettingsPage(driver);
    }

    public CampaignSettingsPage startCampaignCreating(String blockListID) {
        driver.findElement(NEWCAMPAIGNBUTTON).click();
        return new CampaignSettingsPage(driver, blockListID);
    }

    public boolean isSpecifiedCampaignDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNCONTAINER, campaignID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedCampaign() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNDELETEBUTTON, campaignID))).click();
            driver.findElement(CONFIRMDELETIONBUTTON).click();
            WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
            WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULDELETIONNOTIF));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }

    public void openSpecifiedCampaignSettings() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNEDITBUTTON, campaignID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }

    public  boolean isCPCValuesChange(String desktopCPCValue, String mobileCPCValue) {
        int flag = 0;
        try {
            List<WebElement> CPCBlockRows = new ArrayList<>(driver.findElements(By.xpath(String.format(SPECIFIEDCAMPAIGNCPCBLOCKROWS, campaignID))));
            if (CPCBlockRows.size() == 2) {
                if (CPCBlockRows.get(0).getText().contains(desktopCPCValue)) {
                    flag++;
                }
                if (CPCBlockRows.get(1).getText().contains(mobileCPCValue)) {
                    flag++;
                }
            }
            return (flag == 2);
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
            return false;
        }
    }
}
