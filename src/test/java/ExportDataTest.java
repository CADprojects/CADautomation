import base.ExportTestBase;
import helper.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.reporting.CampaignReportPage;
import page.reporting.DeviceGeoReportPage;
import page.reporting.TrafficSourceReportPage;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportDataTest extends ExportTestBase {

    private TrafficSourceReportPage trafficReportPage;
    private DeviceGeoReportPage deviceGeoReportPage;
    private CampaignReportPage campaignReportPage;

    @Test
    public void exportGridDataForWidgetsReportTest() {
        widgetReportPage.generateReportForAll();
        widgetReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(widgetReportPage.getLocatorNameForRequiredGrid(), driver);
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }

    @Test
    public void exportGridDataForTrafficReportTest() {
        trafficReportPage = widgetReportPage.navigateToTrafficSourceReportPage();
        trafficReportPage.generateReportForAll();
        trafficReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(trafficReportPage.getLocatorNameForRequiredGrid(), driver);
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }

    @Test
    public void exportGridDataForDeviceGeoReportTest() {
        deviceGeoReportPage = widgetReportPage.navigateToDeviceGeoReportPage();
        deviceGeoReportPage.generateReportForAll();
        deviceGeoReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(deviceGeoReportPage.getLocatorNameForRequiredGrid(), driver);
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }

    @Test
    public void exportGridDataForCampaignReportTest() {
        campaignReportPage = widgetReportPage.navigateToCampaignReportPage();
        campaignReportPage.generateReportForAll();
        campaignReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(campaignReportPage.getLocatorNameForRequiredGrid(), driver);
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab");
    }
}
