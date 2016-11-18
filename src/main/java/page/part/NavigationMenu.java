package page.part;

import org.openqa.selenium.By;
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
    private WebDriver driver;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
    }

    public WidgetReportPage navigateToWidgetReport() {
        driver.findElement(WIDGETREPORTLINK).click();
        return new WidgetReportPage(driver);
    }

    public TrafficSourceReportPage navigateToTrafficReport() {
        driver.findElement(TRAFFICREPORTLINK).click();
        return new TrafficSourceReportPage(driver);
    }

    public DeviceGeoReportPage navigateToDeviceGeoReport() {
        driver.findElement(DEVICEGEOREPORTLINK).click();
        return new DeviceGeoReportPage(driver);
    }

    public CampaignReportPage navigateToCampaignReport() {
        driver.findElement(CAMPAIGNREPORTLINK).click();
        return new CampaignReportPage(driver);
    }

    public WidgetsPage navigateToWidgetsPage() {
        driver.findElement(WIDGETSLINK).click();
        return new WidgetsPage(driver);
    }

    public DomainsPage navigateToDomainsPage() {
        driver.findElement(DOMAINSLINK).click();
        return new DomainsPage(driver);
    }

    public CampaignsPage navigateToCampaignsPage() {
        driver.findElement(CAMPAIGNSLINK).click();
        return new CampaignsPage(driver);
    }

    public BlockListsPage navigateToBlockListsPage() {
        driver.findElement(BLOCKLISTSLINK).click();
        return new BlockListsPage(driver);
    }

    public GeneralInfoPage navigateToGeneralInfoPage() {
        driver.findElement(GENERALINFOLINK).click();
        return new GeneralInfoPage(driver);
    }

    public PaymentMethodPage navigateToPaymentMethodPage() {
        driver.findElement(PAYMENTMETHODLINK).click();
        return new PaymentMethodPage(driver);
    }

    public EarningsPage navigateToEarningsPage() {
        driver.findElement(EARNINGSLINK).click();
        return new EarningsPage(driver);
    }

    public CustomSourcesPage navigateToCustomSourcesPage() {
        driver.findElement(CUSTOMSOURCESLINK).click();
        return new CustomSourcesPage(driver);
    }

    public TrafficSourcesPage navigateToTrafficSourcesPage() {
        driver.findElement(TRAFFICSOURCESLINK).click();
        return new TrafficSourcesPage(driver);
    }

    public CampaignDepositsPage navigateToCampaignDepositsPage() {
        driver.findElement(CAMPAIGNDEPOSISTSLINK).click();
        return new CampaignDepositsPage(driver);
    }

    public AutoDepositPage navigateToAutoDepositPage() {
        driver.findElement(AUTODEPOSITSLINK).click();
        return new AutoDepositPage(driver);
    }

    public WidgetReportPage navigateToHomePage() {
        driver.findElement(HOMEPAGELINK).click();
        return new WidgetReportPage(driver);
    }



}
