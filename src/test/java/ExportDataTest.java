import base.ExportTestBase;
import helper.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.reporting.CampaignReportPage;
import page.reporting.DeviceGeoReportPage;
import page.reporting.TrafficSourceReportPage;
import page.settings.EarningsPage;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportDataTest extends ExportTestBase {

    private TrafficSourceReportPage trafficReportPage;
    private DeviceGeoReportPage deviceGeoReportPage;
    private CampaignReportPage campaignReportPage;
    private EarningsPage earningsPage;

    @Test
    public void exportGridDataForWidgetsReportTest() {
        widgetReportPage.generateReportForAll();
        widgetReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(widgetReportPage.getLocatorNameForRequiredGrid(), driver, widgetReportPage.getRegex());
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Domains tab on Widgets report page");
    }

    @Test
    public void exportGridDataForTrafficReportTest() {
        trafficReportPage = widgetReportPage.navigateToTrafficSourceReportPage();
        trafficReportPage.generateReportForAll();
        trafficReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(trafficReportPage.getLocatorNameForRequiredGrid(), driver, trafficReportPage.getRegex());
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Source and Campaign tab  on Source&Campaign report page");
    }

    @Test
    public void exportGridDataForDeviceGeoReportTest() {
        deviceGeoReportPage = widgetReportPage.navigateToDeviceGeoReportPage();
        deviceGeoReportPage.generateReportForAll();
        deviceGeoReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(deviceGeoReportPage.getLocatorNameForRequiredGrid(), driver, deviceGeoReportPage.getRegex());
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Device and Geo tab on Device&Geo report page");
    }

    @Test
    public void exportGridDataForCampaignReportTest() {
        campaignReportPage = widgetReportPage.navigateToCampaignReportPage();
        campaignReportPage.generateReportForAll();
        campaignReportPage.exportGeneratedDataToExcel();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(campaignReportPage.getLocatorNameForRequiredGrid(), driver, campaignReportPage.getRegex());
        List<List<String>> dataFromExcel = DataUtils.getInfoFromExcelFile(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file for Campaign tab on Campaign report page");
    }

    @Test
    public void exportEarningsTest() {
        earningsPage = widgetReportPage.navigateToEarningsPage();
        earningsPage.exportDataToExcel();
        List<List<String>> dataFromUIGrid = earningsPage.getDataFromUIGrid();
        List<List<String>> dataFromExcel = earningsPage.getDataFromExcel(FOLDERFOREXPORT);
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromExcel), "Data in grid on UI is differ from data exported to excel file on Earnings page");
    }
}
