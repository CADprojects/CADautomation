package base;

import org.openqa.selenium.WebDriver;
import page.ContactPage;
import page.HelpHomePage;
import page.part.NavigationMenu;
import page.part.TopMenu;
import page.publishing.WidgetsPage;
import page.reporting.CampaignReportPage;
import page.reporting.DeviceGeoReportPage;
import page.reporting.TrafficSourceReportPage;
import page.reporting.WidgetReportPage;
import page.settings.*;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class PageBase {

    protected final NavigationMenu NAVIGATIONMENU;
    protected final TopMenu TOPMENU;
    protected static WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        NAVIGATIONMENU = new NavigationMenu(driver);
        TOPMENU = new TopMenu(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public TrafficSourceReportPage navigateToTrafficSourceReportPage() {
        return NAVIGATIONMENU.navigateToTrafficSourceReportPage();
    }

    public DeviceGeoReportPage navigateToDeviceGeoReportPage() {
        return  NAVIGATIONMENU.navigateToDeviceGeoReport();
    }

    public CampaignReportPage navigateToCampaignReportPage() {
        return  NAVIGATIONMENU.navigateToCampaignReport();
    }

    public WidgetReportPage navigateToWidgetReportPage() {
        return  NAVIGATIONMENU.navigateToWidgetReport();
    }

    public HelpHomePage navigateToHelpPage() {
        return TOPMENU.navigateToHelpHomePage();
    }

    public ContactPage navigateToContactPage() {
        return TOPMENU.navigateToContactPage();
    }

    public GeneralInfoPage navigateToGeneralInfoPage() {
        return TOPMENU.navigateToGeneralInfoPage();
    }

    public AutoDepositPage navigateToAutoDepositPage() {
        return NAVIGATIONMENU.navigateToAutoDepositPage();
    }

    public PaymentMethodPage navigateToPaymentMethodPage() {
        return NAVIGATIONMENU.navigateToPaymentMethodPage();
    }

    public EarningsPage navigateToEarningsPage() {
        return NAVIGATIONMENU.navigateToEarningsPage();
    }

    public CampaignDepositsPage navigateToCampaignDepositsPage() {
        return NAVIGATIONMENU.navigateToCampaignDepositsPage();
    }

    public WidgetsPage navigateToWidgetsPage() {
        return NAVIGATIONMENU.navigateToWidgetsPage();
    }
}

