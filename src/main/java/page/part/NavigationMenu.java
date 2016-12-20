package page.part;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import page.advertising.*;
import page.publishing.*;
import page.reporting.*;
import page.settings.*;

import static helper.Locators.get;

public class NavigationMenu {

    private static final By WIDGETREPORTLINK = get("NavigationMenu.WidgetReportLink");
    private static final By TRAFFICREPORTLINK = get("NavigationMenu.TrafficReportLink");
    private static final By DEVICEGEOREPORTLINK = get("NavigationMenu.DeviceGeoReportLink");
    private static final By CAMPAIGNREPORTLINK = get("NavigationMenu.CampaignReportLink");
    private static final By WIDGETSLINK = get("NavigationMenu.WidgetsLink");
    private static final By DOMAINSLINK = get("NavigationMenu.DomainsLink");
    private static final By CAMPAIGNSLINK = get("NavigationMenu.CampaignsLink");
    private static final By BLOCKLISTSLINK = get("NavigationMenu.BlockListsLink");
    private static final By GENERALINFOLINK = get("NavigationMenu.GeneralInfoLink");
    private static final By PAYMENTMETHODLINK = get("NavigationMenu.PaymentMethodLink");
    private static final By EARNINGSLINK = get("NavigationMenu.EarningsLink");
    private static final By CUSTOMSOURCESLINK = get("NavigationMenu.CustomSourcesLink");
    private static final By TRAFFICSOURCESLINK = get("NavigationMenu.TrafficSourcesLink");
    private static final By CAMPAIGNDEPOSISTSLINK = get("NavigationMenu.CampaignDepositsLink");
    private static final By AUTODEPOSITSLINK = get("NavigationMenu.AutoDepositLink");
    private static final By HOMEPAGELINK = get("NavigationMenu.HomePageLink");
    private static final By PUBLISHINGTAB = get("NavigationMenu.PublishingTab");
    private static final By ADVERTISINGTAB = get("NavigationMenu.AdvertisingTab");
    private WebDriver driver;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToWidgetReport() {
        driver.findElement(WIDGETREPORTLINK).click();
    }

    public void navigateToTrafficSourceReportPage() {
        driver.findElement(TRAFFICREPORTLINK).click();
    }

    public void navigateToDeviceGeoReport() {
        driver.findElement(DEVICEGEOREPORTLINK).click();
    }

    public void navigateToCampaignReport() {
        driver.findElement(CAMPAIGNREPORTLINK).click();
    }

    public void navigateToWidgetsPage() {
        driver.findElement(WIDGETSLINK).click();
    }

    public void navigateToDomainsPage() {
        driver.findElement(DOMAINSLINK).click();
    }

    public void navigateToCampaignsPage() {
        driver.findElement(CAMPAIGNSLINK).click();
    }

    public void navigateToBlockListsPage() {
        driver.findElement(BLOCKLISTSLINK).click();
    }

    public void navigateToGeneralInfoPage() {
        driver.findElement(GENERALINFOLINK).click();
    }

    public void navigateToPaymentMethodPage() {
        driver.findElement(PAYMENTMETHODLINK).click();
    }

    public void navigateToEarningsPage() {
        driver.findElement(EARNINGSLINK).click();
    }

    public void navigateToCustomSourcesPage() {
        driver.findElement(CUSTOMSOURCESLINK).click();
    }

    public void navigateToTrafficSourcesPage() {
        driver.findElement(TRAFFICSOURCESLINK).click();
    }

    public void navigateToCampaignDepositsPage() {
        driver.findElement(CAMPAIGNDEPOSISTSLINK).click();
    }

    public void navigateToAutoDepositPage() {
        driver.findElement(AUTODEPOSITSLINK).click();
    }

    public void navigateToHomePage() {
        driver.findElement(HOMEPAGELINK).click();
    }

    public boolean isPublishingTabDisplayed() {
        try {
            return driver.findElement(PUBLISHINGTAB).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isAdvertisingTabDisplayed() {
        try {
        return driver.findElement(ADVERTISINGTAB).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
