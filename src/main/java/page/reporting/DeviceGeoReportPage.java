package page.reporting;

import base.PublisherReportPageBase;
import helper.DatabaseUtils;
import helper.GraphUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class DeviceGeoReportPage extends PublisherReportPageBase {

    private static final String STARTDATE = "06/01/2016";
    private static final String ENDDATE = "10/31/2016";
    private static final String DEVICEGEOREPORTMODELSCREEN = "/src/main/resources/deviceGeoReportAllMetrics.png";
    private static final String DEVICEGEOGRIDSPNAME = "db.deviceGeoGridSP";
    private static final By GRIDIMPCOLUMNLINK = get("DeviceAndGeoReportPage.GridImpsLink");

    public DeviceGeoReportPage(WebDriver driver) {
        super(driver);
    }

    public void generateReportForAll() {
        generateCustomDateRange(STARTDATE, ENDDATE);
        buildReport();
        WaitersUtils.getWaiter(driver, 100).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
    }

    public boolean isGeneratedGraphCorrect() {
        return GraphUtils.compareGraphs(DEVICEGEOREPORTMODELSCREEN);
    }

    public List<List<String>> getDeviceGeoReportDataFromDB() {
        return DatabaseUtils.getInfoFromDB(DatabaseUtils.connectToDB(), DEVICEGEOGRIDSPNAME);
    }

    public void groupGridByImps() {
        driver.findElement(GRIDIMPCOLUMNLINK).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
    }
}
