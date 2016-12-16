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
    private static final String WIDGETIDFIELD = "//span[@class='widgetTitleId'][contains(text(),'%s')]";
    private static final String SPECIFIEDWIDGETEDITBUTTON = "//span[@class='widgetTitleId'][contains(text(),'%s')]/../..//a[@id='lbEdit']";
    private static final String SPECIFIEDWIDGETDELBUTTON = "//a[contains(@onclick, '%s')]";
    private static final String SPECIFIEDWIDGETADSPERACNTAGECONTAINER = "//span[@class='widgetTitleId'][contains(text(),'%s')]/../..//span[contains(text(),'Ads')]/..";
    private String widgetID;


    public WidgetsPage(WebDriver driver) {
        super(driver);
    }

    public WidgetsPage(WebDriver driver, String widgetID) {
        super(driver);
        this.widgetID = widgetID;
    }

    public WidgetSettingsPage startWidgetCreating() {
        driver.findElement(NEWWIDGETBUTTON).click();
        return new WidgetSettingsPage(driver);
    }

    public void deleteSpecifiedWidget() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDWIDGETDELBUTTON, widgetID))).click();
            WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(WIDGETSUCCESSFULDELETIONNOTIF));
            WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(WIDGETSUCCESSFULDELETIONNOTIF));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified widget wasn't created early" + ex.getMessage());
        }
        driver.findElement(CONFIRMDELETEWIDGETBUTTON).click();
    }

    public boolean isWidgetDisplayed() {
        try {
            return driver.findElement(By.xpath(String.format(WIDGETIDFIELD, widgetID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void openSpecifiedWidgetSettings() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDWIDGETEDITBUTTON, widgetID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified widget wasn't created early" + ex.getMessage());
        }
    }

    public boolean isAdsPercantageChanged(String newPercValue) {
        try {
            String adsPercantage = driver.findElement(By.xpath(String.format(SPECIFIEDWIDGETADSPERACNTAGECONTAINER, widgetID))).getText();
            return adsPercantage.contains(newPercValue);
        } catch (NoSuchElementException ex) {
            System.out.println("Specified widget wasn't created early" + ex.getMessage());
            return false;
        }

    }
}
