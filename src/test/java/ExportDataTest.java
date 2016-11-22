import base.ExportTestBase;
import helper.ExportDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportDataTest extends ExportTestBase {

    @Test
    public void exportGridDataForWidgetsDomainsTest() {
        widgetReportPage.generateReportForAllDomainsWidgets();
        widgetReportPage.exportGeneratedDataToExcel();
        List<String> dataFromUIGrid = ExportDataUtils.getInfoFromRequiredGrid(widgetReportPage.getLocatorNameForRequiredGrid(), driver);
        List<String> dataFromExcel = ExportDataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(ExportDataUtils.compareExcelAndUI(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }
}
