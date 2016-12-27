package page.settings;

import base.SourcePageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import page.part.CustomSourceSettingsPopUp;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class CustomSourcesPage extends SourcePageBase {

    private final CustomSourceSettingsPopUp CUSTOMSOURCESETTINGSPOPUP;
    private static final By NEWCSOURCEBUTTON = get("CustomSourcesPage.NewSourceButton");
    private static final By SUCCESSFULDELETIONNOTIF = get("SourcePageBase.SuccessfulSourceDeletionNotif");
    private static final String SPECIFIEDSOURCECONTAINER = "//div[@class='campaignInfo']/div[1][contains(text(),'%s')]/..";
    private static final String SPECIFIEDSOURCEDELETEBUTTON = "//div[@class='campaignInfo']/div[1][contains(text(),'%s')]/..//a[@id='lbDelete']";
    private static final String SPECIFIEDSOURCEEDITBUTTON = "//div[@class='campaignInfo']/div[1][contains(text(),'%s')]/..//a[@id='lbEdit']";
    private static final String SPECIFIEDSOURCEADDBUTTON = "//div[@class='campaignInfo']/div[1][contains(text(),'%s')]/..//a[@id='lbArticles']";
    private static final String SPECIFIEDSOURCEWIDGETSLIST = "//div[@class='campaignInfo']/div[1][contains(text(),'%s')]/../div[3]";

    public CustomSourcesPage(WebDriver driver) {
        super(driver);
        CUSTOMSOURCESETTINGSPOPUP = new CustomSourceSettingsPopUp(driver);
    }

    public CustomSourceContentPage startCustomSourceCreation() {
        startSourceCreation(NEWCSOURCEBUTTON);
        return new CustomSourceContentPage(driver);
    }

    public boolean isSpecifiedSuctomSourceDisplayed() {
        return isSpecifiedSourceDisplayed(CUSTOMSOURCESETTINGSPOPUP.getSourceName(), SPECIFIEDSOURCECONTAINER);
    }

    public void deleteSpecifiedSuctomSource() {
        deleteSpecifiedSource(CUSTOMSOURCESETTINGSPOPUP.getSourceName(), SPECIFIEDSOURCEDELETEBUTTON, SUCCESSFULDELETIONNOTIF);
    }

    public void openSpecifiedSuctomSourceSettings() {
        openSpecifiedSourceSettings(CUSTOMSOURCESETTINGSPOPUP.getSourceName(), SPECIFIEDSOURCEEDITBUTTON);
    }

    public boolean isWidgetsListEmptyForSpecifiedCustomSource() {
        try{
            return driver.findElement(By.xpath(String.format(SPECIFIEDSOURCEWIDGETSLIST, CUSTOMSOURCESETTINGSPOPUP.getSourceName()))).getText().isEmpty();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified custom source wasn't found");
            return false;
        }
    }

    public CustomSourceContentPage openSourceContentPage() {
        driver.findElement(By.xpath(String.format(SPECIFIEDSOURCEADDBUTTON, CUSTOMSOURCESETTINGSPOPUP.getSourceName()))).click();
        return new CustomSourceContentPage(driver);
    }

    public void addNewCustomSource() {
        CUSTOMSOURCESETTINGSPOPUP.addSourceName();
        CUSTOMSOURCESETTINGSPOPUP.setManualContentAdding();
        CUSTOMSOURCESETTINGSPOPUP.saveNewSourceSettings();
    }

    public void addRemoveSpecifiedServeWidget(String widgetID) {
        CUSTOMSOURCESETTINGSPOPUP.addRemoveServeWidget(widgetID);
    }

    public void saveCustomSourceSettingsChanges() {
        CUSTOMSOURCESETTINGSPOPUP.saveExistingSourceSettings();
    }
}
