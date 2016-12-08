package page.reporting;

import base.PublisherReportPageBase;
import helper.DatabaseUtils;
import helper.GraphUtils;
import helper.WaitersUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class TrafficSourceReportPage extends PublisherReportPageBase {

    private static final String STARTDATE = "01/01/2016";
    private static final String ENDDATE = "09/24/2016";
    private static final String TRAFFICREPORTMODELSCREEN = "/src/main/resources/trafficSourceReportAllMetrics.png";
    private static final String TRAFFICGRIDSPNAME = "db.trafficGridSP";

    public TrafficSourceReportPage(WebDriver driver) {
        super(driver);
    }

    public void generateReportForAll() {
        generateCustomDataRange(STARTDATE, ENDDATE);
        buildReport();
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
    }

    public boolean isGeneratedGraphCorrect() {
        return GraphUtils.compareGraphs(TRAFFICREPORTMODELSCREEN);
    }

    public List<List<String>> getTrafficReportDataFromDB() {
        return DatabaseUtils.getInfoFromDB(DatabaseUtils.connectToDB(), TRAFFICGRIDSPNAME);
    }

}
