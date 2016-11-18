package page.reporting;

import base.PublisherReportPageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class TrafficSourceReportPage extends PublisherReportPageBase {

    protected WebDriver driver;

    public TrafficSourceReportPage(WebDriver driver) {
        super(driver);
    }
}
