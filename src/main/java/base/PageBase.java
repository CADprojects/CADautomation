package base;

import org.openqa.selenium.WebDriver;
import page.part.NavigationMenu;
import page.part.TopMenu;
import page.reporting.CampaignReportPage;
import page.reporting.DeviceGeoReportPage;
import page.reporting.TrafficSourceReportPage;
import page.reporting.WidgetReportPage;

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
}

