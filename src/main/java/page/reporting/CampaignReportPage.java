package page.reporting;

import base.ReportPageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class CampaignReportPage extends ReportPageBase {

    protected WebDriver driver;

    public CampaignReportPage(WebDriver driver) {
        super(driver);
    }
}
