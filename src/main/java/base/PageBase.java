package base;

import org.openqa.selenium.WebDriver;
import page.ContactPage;
import page.HelpHomePage;
import page.part.NavigationMenu;
import page.part.TopMenu;
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
        NAVIGATIONMENU.navigateToTrafficReport();
        return new TrafficSourceReportPage(driver);
    }

    public DeviceGeoReportPage navigateToDeviceGeoReportPage() {
        NAVIGATIONMENU.navigateToDeviceGeoReport();
        return new DeviceGeoReportPage(driver);
    }

    public CampaignReportPage navigateToCampaignReportPage() {
        NAVIGATIONMENU.navigateToCampaignReport();
        return new CampaignReportPage(driver);
    }

    public WidgetReportPage navigateToWidgetReportPage() {
        NAVIGATIONMENU.navigateToWidgetReport();
        return new WidgetReportPage(driver);
    }

    public HelpHomePage navigateToHelpPage() {
        TOPMENU.navigateToHelpHomePage();
        return new HelpHomePage(driver);
    }

    public ContactPage navigateToContactPage() {
        TOPMENU.navigateToContactPage();
        return new ContactPage(driver);
    }

    public GeneralInfoPage navigateToGeneralInfoPage() {
        TOPMENU.navigateToGeneralInfoPage();
        return new GeneralInfoPage(driver);
    }

    public AutoDepositPage navigateToAutoDepositPage() {
        NAVIGATIONMENU.navigateToAutoDepositPage();
        return new AutoDepositPage(driver);
    }

    public PaymentMethodPage navigateToPaymentMethodPage() {
        NAVIGATIONMENU.navigateToPaymentMethodPage();
        return new PaymentMethodPage(driver);
    }

    public EarningsPage navigateToEarningsPage() {
        NAVIGATIONMENU.navigateToEarningsPage();
        return new EarningsPage(driver);
    }

    public CampaignDepositsPage navigateToCampaignDepositsPage() {
        NAVIGATIONMENU.navigateToCampaignDepositsPage();
        return new CampaignDepositsPage(driver);
    }
}

