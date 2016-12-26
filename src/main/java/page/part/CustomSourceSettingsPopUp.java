package page.part;

import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 21.12.2016.
 */
public class CustomSourceSettingsPopUp {

    private static final By SOURCENAMEINPUT = get("CustomSourceSettingsPopUp.SourceNameInput");
    private static final By MANUALLYBUTTON = get("CustomSourceSettingsPopUp.ManuallyButton");
    private static final By SAVEBUTTON = get("CustomSourceSettingsPopUp.SaveButton");
    private static final By SUCCESSFULCREATIONNOTIF = get("CustomSourceSettingsPopUp.SuccessfulSourceCreationNotif");
    private static final By SUCCESSFULEDITINGNOTIF = get("CustomSourceSettingsPopUp.SuccessfulSourceEditingNotif");
    private static final String SPECIFIEDWIDGETCHECKBOX = "input[value='%s']";
    private static final String CUSTOMSOURCEMASK = "TestSource";
    private String sourceName;
    private WebDriver driver;

    public CustomSourceSettingsPopUp(WebDriver driver) {
        this.driver = driver;
    }

    public void addSourceName() {
        sourceName = CUSTOMSOURCEMASK + RandomizersUtils.randomPrefix();
        driver.findElement(SOURCENAMEINPUT).sendKeys(sourceName);
    }

    public void setManualContentAdding() {
        driver.findElement(MANUALLYBUTTON).click();
    }

    public void addRemoveServeWidget(String widgetID) {
        driver.findElement(By.cssSelector(String.format(SPECIFIEDWIDGETCHECKBOX, widgetID))).click();
    }

    public void saveNewSourceSettings() {
        driver.findElement(SAVEBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULCREATIONNOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULCREATIONNOTIF));
    }

    public void saveExistingSourceSettings() {
        driver.findElement(SAVEBUTTON).click();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULEDITINGNOTIF));
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULEDITINGNOTIF));
    }

    public String getSourceName() {
        return sourceName;
    }
}
