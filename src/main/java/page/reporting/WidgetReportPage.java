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
public class WidgetReportPage extends PublisherReportPageBase {

    private static final String STARTDATE = "11/16/2013";
    private static final String ENDDATE = "11/16/2015";
    private static final String WIDGETREPORTMODELSCREEN = "/src/main/resources/widgetReportAllMetrics.png";
    private static final String WIDGETGRIDSPNAME = "db.widgetGridSP";
    private static final By ACCOUNTTABBUTTON = get("WidgetReportPage.AccountTabButton");

    public WidgetReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogInSuccessful(){
        return TOPMENU.isLogOutLinkDisplayed();
    }

    public void generateReportForAll() {
        generateCustomDateRange(STARTDATE, ENDDATE);
        buildReport();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
    }

    public boolean isGeneratedGraphCorrect(){
        return GraphUtils.compareGraphs(WIDGETREPORTMODELSCREEN);
    }

    public void chooseAccountTab() {
        driver.findElement(ACCOUNTTABBUTTON).click();
        driver.switchTo().defaultContent();
    }

    public List<List<String>> getWidgetReportDataFromDB() {
        return DatabaseUtils.getInfoFromDB(DatabaseUtils.connectToDB(), WIDGETGRIDSPNAME);
    }


}
