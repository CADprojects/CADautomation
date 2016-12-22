package base;

import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.part.AddContentRequiredOptions;
import page.part.AddContentURLOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 21.12.2016.
 */
public class ContentPageBase extends PageBase {

    private static final By CAMPAIGNIDFIELD = get("ContentPageBase.SourceIDField");
    private static final By ALLSOURCESLINK = get("ContentPageBase.AllSourcesLinks");
    private static final By ADDCONTENTBUTTON = get("ContentPageBase.AddContentButton");
    private static final By SUCCESSFULADCREATIONNOTIF = get("ContentPageBase.SuccessfulAdCreationNotif");
    private static final By SUCCESSFULADDELETIONNOTIF = get("ContentPageBase.SuccessfulAdDeletionNotif");
    private static final By CONFIRMADDELETIONBUTTON = get("ContentPageBase.AdDeletionConfirmButton");
    private static final String SPECIFIEDADTITLEFIELD = "//span[@class='title'][contains(text(),'%s')]";
    private static final String SPECIFIEDADDELETEBUTTON = "//span[@class='title'][contains(text(),'%s')]/../../..//a[@id='lbRemove']";
    private static final String SPECIFIEDADEDITBUTTON = "//span[@class='title'][contains(text(),'%s')]/../../..//a[@id='lbSettings']";
    private final AddContentRequiredOptions ADDCONTENTREQUIREDOPTIONS;
    private final AddContentURLOptions ADDCONTENTURLOPTIONS;
    private String sourceID;

    public ContentPageBase(WebDriver driver) {
        super(driver);
        ADDCONTENTREQUIREDOPTIONS = new AddContentRequiredOptions(driver);
        ADDCONTENTURLOPTIONS = new AddContentURLOptions(driver);
    }

    public void setSourceID() {
        Pattern pattern = Pattern.compile("\\d+");
        String sourceId = null;
        Matcher matcher = pattern.matcher(driver.findElement(CAMPAIGNIDFIELD).getText());
        if (matcher.find()) {
            sourceId = matcher.group(0);
        }
        this.sourceID = sourceId;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void startAddingContent() {
        driver.findElement(ADDCONTENTBUTTON).click();
    }

    public void addRequiredOptions(By titleInput) {
        ADDCONTENTURLOPTIONS.addAdURLWithoutScan();
        ADDCONTENTREQUIREDOPTIONS.addAdTitle(titleInput);
        ADDCONTENTREQUIREDOPTIONS.addAdImage();
    }

    public void finishAddingContent() {
        ADDCONTENTREQUIREDOPTIONS.saveNewAdSettings();
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULADCREATIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULADCREATIONNOTIF));
        ADDCONTENTURLOPTIONS.cancelAdAdd();
    }

    public boolean isAdDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDADTITLEFIELD, ADDCONTENTREQUIREDOPTIONS.getAdTitle()))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteAd() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDADDELETEBUTTON, ADDCONTENTREQUIREDOPTIONS.getAdTitle()))).click();
            driver.findElement(CONFIRMADDELETIONBUTTON).click();
            WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULADDELETIONNOTIF));
            WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULADDELETIONNOTIF));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified ad wasn't found");
        }
    }

    public void openSpecifiedAdSettings() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDADEDITBUTTON, ADDCONTENTREQUIREDOPTIONS.getAdTitle()))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified ad wasn't found");
        }
    }

    public void changeAdTitle(By titleInput) {
        ADDCONTENTREQUIREDOPTIONS.addAdTitle(titleInput);
    }

    public void saveAdChanges() {
        ADDCONTENTREQUIREDOPTIONS.saveSpecifiedAdSettings();
    }

    public void returnToAllSourcesPage() {
        setSourceID();
        driver.findElement(ALLSOURCESLINK).click();
    }
}
