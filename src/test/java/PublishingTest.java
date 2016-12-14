import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.publishing.InstallationCodePage;
import page.publishing.WidgetSettingsPage;
import page.publishing.WidgetsPage;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class PublishingTest extends TestBase {

    private WidgetsPage widgetsPage;
    private WidgetSettingsPage widgetSettingsPage;
    private InstallationCodePage installationCodePage;
    private String newSponsoredLinksNumber = "3";
    private String newAdsPercantage = "33.33";

    @Test
    public void widgetCreationTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetAdding();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        widgetsPage = installationCodePage.returnToWidgetsPage();
        Assert.assertTrue(widgetsPage.isWidgetCreated(), "New widget wasn't created");
    }

    @Test
    public void widgetDeletionTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetAdding();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        widgetsPage = installationCodePage.returnToWidgetsPage();
        widgetsPage.deleteSpecifiedWidget();
        Assert.assertFalse(widgetsPage.isWidgetDeleted(), "Widget wasn't deleted");
    }

    @Test
    public void changeWidgetTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetAdding();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        widgetsPage = installationCodePage.returnToWidgetsPage();
        widgetsPage.openSpecifiedWidgetSettings();
        widgetSettingsPage.enterSponsoredLinksNumber(newSponsoredLinksNumber);
        widgetSettingsPage.saveWidgetSettings();
        widgetSettingsPage.backToWidgetsPageAfterSettingsSave();
        Assert.assertTrue(widgetsPage.isAdsPercantageChanged(newAdsPercantage), "Ads percantage value wasn't changed");
    }
}
