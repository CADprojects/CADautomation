package base;

import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static helper.Locators.get;
/**
 * Created by Andrei.Ostrovski on 17.11.2016.
 */
public class ReportPageBase extends PageBase {

    private static final By REPORTHEADERNAME = get("ReportBase.ReportHeader");
    private static final By DATAPICKERDROPDOWN = get("ReportBase.DataPickerDropdown");
    private static final By STARTDATEINPUT = get("ReportBase.StartDateInput");
    private static final By ENDDATEINPUT = get("ReportBase.EndDateInput");
    private static final By APPLYBUTTON = get("ReportBase.GenerateReportApplyButton");
    private static final By GROUPBYDROPDOWN = get("ReportBase.GroupByDropdown");
    private static final By EXPORTLINK = get("ReportBase.ExportToExcelLink");
    private static final By CURRENTGENERATEDGRIDHEADER = get("ReportBase.CurrentGeneratedGridHeader");
    protected static final By LOADINGPROGRESSICON = get("ReportBase.LoadingProgressIcon");
    private static final By METRICEXCEPTIMPSCHECKBOXES = get("ReportBase.AggregateCheckboxesExceptImps");
    private static final By GRAPHCONTAINER = get("ReportBase.AggregateGraphContainer");
    private static final String DROPDOWNCUSTOMVALUE = "custom";
    private static final String REGEX = "[%$,-]";

    public ReportPageBase(WebDriver driver) {
        super(driver);
    }

    public String getRegex() {
        return REGEX;
    }

    protected void generateCustomDataRange(String startDate, String endDate) {
        Select select = new Select(driver.findElement(DATAPICKERDROPDOWN));
        select.selectByValue(DROPDOWNCUSTOMVALUE);
        driver.findElement(STARTDATEINPUT).clear();
        driver.findElement(STARTDATEINPUT).sendKeys(startDate);
        WaitersUtils.getWaiter(driver, 10).until(ExpectedConditions.elementToBeClickable(ENDDATEINPUT));
        driver.findElement(ENDDATEINPUT).clear();
        driver.findElement(ENDDATEINPUT).sendKeys(endDate);
    }

    protected void buildReport() {
        driver.findElement(APPLYBUTTON).click();
    }

    public void exportGeneratedDataToExcel() {
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
        driver.findElement(EXPORTLINK).click();
    }

    public String getLocatorNameForRequiredGrid() {
        String reportHeader = driver.findElement(REPORTHEADERNAME).getText().replaceAll(" ","").replace("&", "And") + "Page";
        String currentGeneratedGridHeader = driver.findElement(CURRENTGENERATEDGRIDHEADER).getText().replaceAll(" ","") + "StatGridBodyRows";
        return reportHeader+"."+currentGeneratedGridHeader;
    }

    public void addAllmetrics() {
        driver.findElement(GRAPHCONTAINER).click();
        List<WebElement> metricCheckboxes = driver.findElements(METRICEXCEPTIMPSCHECKBOXES);
        for(WebElement metricButton: metricCheckboxes) {
            metricButton.click();
        }
    }
}
