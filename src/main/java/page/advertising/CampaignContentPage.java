package page.advertising;

import base.PageBase;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.part.AddContentSettingsPopUp;
import page.part.AddContentURLPopUp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 12.12.2016.
 */
public class CampaignContentPage extends PageBase {

    private static final By CAMPAIGNIDFIELD = get("CampaignContentPage.CampaignIDField");
    private static final By MYCAMPAIGNSLINK = get("CampaignContentPage.MyCampaignsLinks");
    private static final By ADDCONTENTBUTTON = get("CampaignContentPage.AddContentButton");
    private static final By SUCCESSFULADCREATIONNOTIF = get("CampaignContentPage.SuccessfulAdCreationNotif");
    private static final By SUCCESSFULADDELETIONNOTIF = get("CampaignContentPage.SuccessfulAdDeletionNotif");
    private static final String SPECIFIEDADTITLEFIELD = "//span[@class='title'][contains(text(),'%s')]";
    private static final String SPECIFIEDADDELETEBUTTON = "//span[@class='title'][contains(text(),'%s')]/../../..//a[@id='lbRemove']";
    private static final String SPECIFIEDADEDITBUTTON = "//span[@class='title'][contains(text(),'%s')]/../../..//a[@id='lbSettings']";
    private static final By CONFIRMADDELETIONBUTTON = get("CampaignContentPage.AdDeletionConfirmButton");
    private final AddContentSettingsPopUp ADDCONTENTSETTINGSPOPUP;
    private final AddContentURLPopUp ADDCONTENTURLPOPUP;

    public CampaignContentPage(WebDriver driver) {
        super(driver);
        ADDCONTENTSETTINGSPOPUP = new AddContentSettingsPopUp(driver);
        ADDCONTENTURLPOPUP = new AddContentURLPopUp(driver);
    }

    public CampaignsPage returnToCampaignsPage() {
        Pattern pattern = Pattern.compile("[0-9]+");
        String campaignId = null;
        Matcher matcher = pattern.matcher(driver.findElement(CAMPAIGNIDFIELD).getText());
        if (matcher.find()) {
            campaignId = matcher.group(0);
        }
        driver.findElement(MYCAMPAIGNSLINK).click();
        return new CampaignsPage(driver, campaignId);
    }

    public void startAddingContent() {
        driver.findElement(ADDCONTENTBUTTON).click();
    }

    public void addAd() {
        startAddingContent();
        ADDCONTENTURLPOPUP.addAdURLWithoutScan();
        ADDCONTENTSETTINGSPOPUP.addAdTitle();
        ADDCONTENTSETTINGSPOPUP.addAdImage();
        ADDCONTENTSETTINGSPOPUP.addAdShortTitle();
        ADDCONTENTSETTINGSPOPUP.addAdShortSummary();
        ADDCONTENTSETTINGSPOPUP.addAdSummary();
        ADDCONTENTSETTINGSPOPUP.addAdCallToAction();
        ADDCONTENTSETTINGSPOPUP.addAdLogo();
        ADDCONTENTSETTINGSPOPUP.saveNewAdSettings();
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULADCREATIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULADCREATIONNOTIF));
        ADDCONTENTURLPOPUP.cancelAdAdd();
    }

    public boolean isAdDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDADTITLEFIELD, ADDCONTENTSETTINGSPOPUP.getAdTitle()))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteAd() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDADDELETEBUTTON, ADDCONTENTSETTINGSPOPUP.getAdTitle()))).click();
            driver.findElement(CONFIRMADDELETIONBUTTON).click();
            WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULADDELETIONNOTIF));
            WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULADDELETIONNOTIF));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified ad wasn't found");
        }
    }

    public void openSpecifiedAdSettings() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDADEDITBUTTON, ADDCONTENTSETTINGSPOPUP.getAdTitle()))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified ad wasn't found");
        }
    }

    public void changeAdTitle() {
        ADDCONTENTSETTINGSPOPUP.addAdTitle();
    }

    public void saveAdChanges() {
        ADDCONTENTSETTINGSPOPUP.saveSpecifiedAdSettings();
    }
}
