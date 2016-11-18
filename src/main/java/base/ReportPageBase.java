package base;

import helper.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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
    private static final By LOADINGPROGRESSICON = get("ReportBase.LoadingProgressIcon");
    private static final String DROPDOWNCUSTOMVALUE = "custom";

    public ReportPageBase(WebDriver driver) {
        super(driver);
    }

    protected void generateCustomDataRange(String startDate, String endDate) {
        Select select = new Select(driver.findElement(DATAPICKERDROPDOWN));
        select.selectByValue(DROPDOWNCUSTOMVALUE);
        driver.findElement(STARTDATEINPUT).clear();
        driver.findElement(STARTDATEINPUT).sendKeys(startDate);
        driver.findElement(ENDDATEINPUT).clear();
        driver.findElement(ENDDATEINPUT).sendKeys(endDate);
    }

    protected void buildReport() {
        driver.findElement(APPLYBUTTON).click();
    }

    public void exportGeneratedDataToExcel() {
        Waiter.getWaiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(LOADINGPROGRESSICON));
        driver.findElement(EXPORTLINK).click();
    }

    public String getLocatorNameForRequiredGrid() {
        String reportHeader = driver.findElement(REPORTHEADERNAME).getText().replaceAll(" ","") + "Page";
        String currentGeneratedGridHeader = driver.findElement(CURRENTGENERATEDGRIDHEADER).getText().replaceAll(" ","") + "StatGridBodyData";
        return reportHeader+"."+currentGeneratedGridHeader;
    }
}
