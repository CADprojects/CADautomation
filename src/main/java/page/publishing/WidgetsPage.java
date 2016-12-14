package page.publishing;

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
public class WidgetsPage extends PageBase {

    private static final By NEWWIDGETBUTTON = get("WidgetsPage.NewWidgetButton");
    private static final By CONFIRMDELETEWIDGETBUTTON = get("WidgetsPage.ConfirmDeleteWidgetButton");
    private static final By WIDGETSUCCESSFULDELETIONNOTIF = get("WidgetsPage.WidgetSuccessfulDeletionNotif");
    private String widgetIdField = "//span[@class='widgetTitleId'][contains(text(),'%s')]";
    private String specifiedWidgetEditButton = "//span[@class='widgetTitleId'][contains(text(),'%s')]/../..//a[@id='lbEdit']";
    private String specifiedWidgetDelButton = "//a[contains(@onclick, '%s')]";
    private String specifiedWidgetAdsPeracntageContainer = "//span[@class='widgetTitleId'][contains(text(),'150011')]/../..//span[contains(text(),'Ads')]/..";
    private String widgetID;


    public WidgetsPage(WebDriver driver) {
        super(driver);
    }

    public WidgetsPage(WebDriver driver, String widgetID) {
        super(driver);
        this.widgetID = widgetID;
    }

    public WidgetSettingsPage startWidgetAdding() {
        driver.findElement(NEWWIDGETBUTTON).click();
        return new WidgetSettingsPage(driver);
    }

    public boolean isWidgetCreated() {
        try {
            return driver.findElement(By.xpath(String.format(widgetIdField, widgetID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedWidget() {
        driver.findElement(By.xpath(String.format(specifiedWidgetDelButton, widgetID))).click();
        driver.findElement(CONFIRMDELETEWIDGETBUTTON).click();
    }

    public boolean isWidgetDeleted() {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(WIDGETSUCCESSFULDELETIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(WIDGETSUCCESSFULDELETIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(widgetIdField, widgetID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void openSpecifiedWidgetSettings() {
        driver.findElement(By.xpath(String.format(specifiedWidgetEditButton, widgetID))).click();
    }

    public boolean isAdsPercantageChanged(String newPercValue) {
        String adsPercantage = driver.findElement(By.xpath(String.format(specifiedWidgetAdsPeracntageContainer, widgetID))).getText();
        return adsPercantage.contains(newPercValue);
    }
}
