package page.advertising;

import base.SourcePageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class CampaignsPage extends SourcePageBase {

    private static final By NEWCAMPAIGNBUTTON = get("CampaignsPage.NewCampaignButton");
    private static final By SUCCESSFULDELETIONNOTIF = get("CampaignsPage.SuccessfulCampaignDeletionNotif");
    private static final String SPECIFIEDCAMPAIGNCONTAINER = "//div[@class='campaignInfo']/div[1][text()='%s']/..";
    private static final String SPECIFIEDCAMPAIGNDELETEBUTTON = "//div[@class='campaignInfo']/div[1][text()='%s']/..//a[@id='lbDelete']";
    private static final String SPECIFIEDCAMPAIGNEDITBUTTON = "//div[@class='campaignInfo']/div[1][text()='%s']/..//a[@id='lbEdit']";
    private static final String SPECIFIEDCAMPAIGNCPCBLOCKROWS = "//div[@class='campaignInfo']/div[1][text()='%s']/../div[@class='campaignCPC']/div";

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    public CampaignSettingsPage startCampaignCreation() {
        startSourceCreation(NEWCAMPAIGNBUTTON);
        return new CampaignSettingsPage(driver);
    }

    public boolean isSpecifiedCampaignDisplayed(String campaignID) {
        return isSpecifiedSourceDisplayed(campaignID, SPECIFIEDCAMPAIGNCONTAINER);
    }

    public void deleteSpecifiedCampaign(String campaignID) {
        deleteSpecifiedSource(campaignID, SPECIFIEDCAMPAIGNDELETEBUTTON, SUCCESSFULDELETIONNOTIF);
    }

    public void openSpecifiedCampaignSettings(String campaignID) {
        openSpecifiedSourceSettings(campaignID, SPECIFIEDCAMPAIGNEDITBUTTON);
    }

    public  boolean isCPCValuesChange(String desktopCPCValue, String mobileCPCValue, String campaignID) {
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
