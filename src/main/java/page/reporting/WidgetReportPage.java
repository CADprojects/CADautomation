package page.reporting;

import base.PublisherReportPageBase;
import helper.GraphUtils;
import org.openqa.selenium.WebDriver;
import page.LogInPage;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class WidgetReportPage extends PublisherReportPageBase {

    private static final String STARTDATE = "11/16/2013";
    private static final String ENDDATE = "11/16/2015";
    private static final String WIDGETREPORTMODELSCREEN = "/src/main/resources/widgetReportAllMetrics.png";

    public WidgetReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogInSuccessful(){
        return TOPMENU.isLogOutLinkDisplayed();
    }

    public LogInPage logOut() {
        TOPMENU.navigateToLoginPage();
        return new LogInPage(driver);
    }

    public void generateReportForAllDomainsWidgets() {
        generateCustomDataRange(STARTDATE, ENDDATE);
        buildReport();
    }

    public boolean isGeneratedReportCorrect(){
        return GraphUtils.compareGraphs(WIDGETREPORTMODELSCREEN);
    }
}
