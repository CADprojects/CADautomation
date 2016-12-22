import base.TestBase;
import helper.DataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.reporting.CampaignReportPage;
import page.reporting.DeviceGeoReportPage;
import page.reporting.TrafficSourceReportPage;

import java.util.List;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class ReportingTest extends TestBase {

    private TrafficSourceReportPage trafficReportPage;
    private DeviceGeoReportPage deviceGeoReportPage;
    private CampaignReportPage campaignReportPage;

    @Test
    public void widgetGraphTest() {
        widgetReportPage.generateReportForAll();
        widgetReportPage.addAllmetrics();
        Assert.assertTrue(widgetReportPage.isGeneratedGraphCorrect(), "Generated report is differ from widget report page model");
    }

    @Test
    public void widgetGridTest() {
        widgetReportPage.chooseAccountTab();
        widgetReportPage.generateReportForAll();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(widgetReportPage.getLocatorNameForRequiredGrid(), driver, widgetReportPage.getRegex());
        List<List<String>> dataFromDB = widgetReportPage.getWidgetReportDataFromDB();
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromDB), "Data in grid on UI is differ from data stored in database for Account tab");
    }

    @Test
    public void trafficSourceGraphTest() {
        trafficReportPage = widgetReportPage.navigateToTrafficSourceReportPage();
        trafficReportPage.generateReportForAll();
        trafficReportPage.addAllmetrics();
        Assert.assertTrue(trafficReportPage.isGeneratedGraphCorrect(), "Generated report is differ from widget report page model");
    }

    @Test
    public void trafficSourceGridTest(){
        trafficReportPage = widgetReportPage.navigateToTrafficSourceReportPage();
        trafficReportPage.generateReportForAll();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(trafficReportPage.getLocatorNameForRequiredGrid(), driver, trafficReportPage.getRegex());
        List<List<String>> dataFromDB = trafficReportPage.getTrafficReportDataFromDB();
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromDB), "Data in grid on UI is differ from data stored in database for Account tab");
    }

    @Test
    public void deviceGeoGraphTest() {
        deviceGeoReportPage = widgetReportPage.navigateToDeviceGeoReportPage();
        deviceGeoReportPage.generateReportForAll();
        deviceGeoReportPage.addAllmetrics();
        Assert.assertTrue(deviceGeoReportPage.isGeneratedGraphCorrect(), "Generated report is differ from widget report page model");
    }

    @Test
    public void deviceGeoGridTest(){
        deviceGeoReportPage = widgetReportPage.navigateToDeviceGeoReportPage();
        deviceGeoReportPage.generateReportForAll();
        deviceGeoReportPage.groupGridByImps();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(deviceGeoReportPage.getLocatorNameForRequiredGrid(), driver, deviceGeoReportPage.getRegex());
        List<List<String>> dataFromDB = deviceGeoReportPage.getDeviceGeoReportDataFromDB();
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromDB), "Data in grid on UI is differ from data stored in database for Account tab");
    }

    @Test
    public void campaignGraphTest() {
        campaignReportPage = widgetReportPage.navigateToCampaignReportPage();
        campaignReportPage.generateReportForAll();
        campaignReportPage.addAllmetrics();
        Assert.assertTrue(campaignReportPage.isGeneratedGraphCorrect(), "Generated report is differ from widget report page model");

    }

    @Test
    public void campaignGridTest(){
        campaignReportPage = widgetReportPage.navigateToCampaignReportPage();
        campaignReportPage.chooseAccountTab();
        campaignReportPage.generateReportForAll();
        List<List<String>> dataFromUIGrid = DataUtils.getInfoFromRequiredGrid(campaignReportPage.getLocatorNameForRequiredGrid(), driver, campaignReportPage.getRegex());
        List<List<String>> dataFromDB = campaignReportPage.getCampaignReportDataFromDB();
        Assert.assertTrue(DataUtils.compareData(dataFromUIGrid, dataFromDB), "Data in grid on UI is differ from data stored in database for Account tab");
    }
}
