import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class ReportingTest extends TestBase {

    @Test
    public void widgetGraphTest() {
        widgetReportPage.generateReportForAllDomainsWidgets();
        widgetReportPage.addAllmetrics();
        Assert.assertTrue(widgetReportPage.isGeneratedReportCorrect(), "Generated report is differ from widget report page model");
    }
}
