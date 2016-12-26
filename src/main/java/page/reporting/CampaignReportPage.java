package page.reporting;

import base.ReportPageBase;
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
public class CampaignReportPage extends ReportPageBase {

    private static final String STARTDATE = "11/16/2013";
    private static final String ENDDATE = "11/16/2015";
    private static final String CAMPAIGNREPORTMODELSCREEN = "/src/main/resources/campaignReportAllMetrics.png";
    private static final String CAMPAIGNGRIDSPNAME = "db.campaignGridSP";
    private static final By ACCOUNTTABBUTTON = get("CampaignReportPage.AccountTabButton");

    public CampaignReportPage(WebDriver driver) {
        super(driver);
    }

    public void generateReportForAll() {
        generateCustomDataRange(STARTDATE, ENDDATE);
        buildReport();
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
    }

    public boolean isGeneratedGraphCorrect() {
        return GraphUtils.compareGraphs(CAMPAIGNREPORTMODELSCREEN);
    }

    public List<List<String>> getCampaignReportDataFromDB() {
        return DatabaseUtils.getInfoFromDB(DatabaseUtils.connectToDB(), CAMPAIGNGRIDSPNAME);
    }

    public void chooseAccountTab() {
        driver.findElement(ACCOUNTTABBUTTON).click();
    }
}
