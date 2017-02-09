package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;
/**
 * Created by Andrei.Ostrovski on 17.11.2016.
 */
public class PublisherReportPageBase extends ReportPageBase {

    private static final By DOMAINSDROPDOWN = get("PublisherReportBase.DomainsDropdown");
    private static final By WIDGETSDROPDOWN = get("PublisherReportBase.WidgetsDropdown");

    public PublisherReportPageBase(WebDriver driver) {
        super(driver);
    }
}
