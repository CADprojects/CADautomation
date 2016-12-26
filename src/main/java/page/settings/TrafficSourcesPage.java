package page.settings;

import base.PageBase;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class TrafficSourcesPage extends PageBase {

    private static final By SOURCEINPUT = get("TrafficSourcesPage.SourceInput");
    private static final By CAMPAIGNINPUT = get("TrafficSourcesPage.CampaignInput");
    private static final By SOURCEADDBUTTON = get("TrafficSourcesPage.SourceAddButton");
    private static final By CAMPAIGNADDBUTTON = get("TrafficSourcesPage.CampaignAddButton");
    private static final By SUCCESSFULTRAFFICSOURCEADDNOTIF = get("TrafficSourcesPage.SuccessfulTrafficSourceAddNotif");
    private static final By SUCCESSFULTRAFFICSOURCEREMOVENOTIF = get("TrafficSourcesPage.SuccessfulTrafficSourceRemoveNotif");
    private static final By CONFIRMTRAFFICSOURCEREMOVEBUTTON = get("TrafficSourcesPage.ConfirmTrafficSourceDeletionButton");
    private static final String ADDEDSOURCEVALUEINTABLE = "//div[@id='dim1']//td[text()='%s']";
    private static final String ADDEDCAMPAIGNVALUEINTABLE = "//div[@id='dim2']//td[text()='%s']";
    private static final String SPECIFIEDSOURCEVALUEDELBUTTON = "//div[@id='dim1']//td[@data-value='%s']";
    private static final String SPECIFIEDCAMPAIGNVALUEDELBUTTON = "//div[@id='dim2']//td[@data-value='%s']";
    private static final String TRAFFICSOURCEVALUEMASK = "testsource";
    private static final int LENGTHOFRANDOMPREFIX = 5;
    private String addedSourceValue;
    private String addedCampaignValue;

    public TrafficSourcesPage(WebDriver driver) {
        super(driver);
    }

    public void addNewSourceValue() {
        addedSourceValue = TRAFFICSOURCEVALUEMASK + RandomizersUtils.randomText(LENGTHOFRANDOMPREFIX);
        driver.findElement(SOURCEINPUT).sendKeys(addedSourceValue);
        driver.findElement(SOURCEADDBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEADDNOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEADDNOTIF));
    }

    public void addNewCampaignValue() {
        addedCampaignValue = TRAFFICSOURCEVALUEMASK + RandomizersUtils.randomText(LENGTHOFRANDOMPREFIX);
        driver.findElement(CAMPAIGNINPUT).sendKeys(addedCampaignValue);
        driver.findElement(CAMPAIGNADDBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEADDNOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEADDNOTIF));
    }

    public void deleteSpecifiedSourceValue() {
        driver.findElement(By.xpath(String.format(SPECIFIEDSOURCEVALUEDELBUTTON, addedSourceValue))).click();
        driver.findElement(CONFIRMTRAFFICSOURCEREMOVEBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEREMOVENOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEREMOVENOTIF));
    }

    public void deleteSpecifiedCampaignValue() {
        driver.findElement(By.xpath(String.format(SPECIFIEDCAMPAIGNVALUEDELBUTTON, addedCampaignValue))).click();
        driver.findElement(CONFIRMTRAFFICSOURCEREMOVEBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEREMOVENOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULTRAFFICSOURCEREMOVENOTIF));
    }

    public boolean isAddedSourceValueDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(ADDEDSOURCEVALUEINTABLE, addedSourceValue))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isAddedCampaignValueDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(ADDEDCAMPAIGNVALUEINTABLE, addedCampaignValue))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
