import base.ExportTestBase;
import helper.ExportDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportDataTest extends ExportTestBase {

    private ExportDataUtils verifyExportDataUtils = new ExportDataUtils();

    @Test
    public void exportGridDataForWidgetsDomainsTest() {
        widgetReportPage.generateReportForAllDomainsWidgets();
        widgetReportPage.exportGeneratedDataToExcel();
        List<String> dataFromUIGrid = verifyExportDataUtils.getInfoFromRequiredGrid(widgetReportPage.getLocatorNameForRequiredGrid(), driver);
        List<String> dataFromExcel = verifyExportDataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(verifyExportDataUtils.compareExcelAndUI(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }
}
