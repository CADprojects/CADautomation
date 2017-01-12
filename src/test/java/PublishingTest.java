import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.publishing.DomainsPage;
import page.publishing.InstallationCodePage;
import page.publishing.WidgetSettingsPage;
import page.publishing.WidgetsPage;
import testng.TestListener;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
@Listeners(TestListener.class)
public class PublishingTest extends TestBase {

    private WidgetsPage widgetsPage;
    private WidgetSettingsPage widgetSettingsPage;
    private InstallationCodePage installationCodePage;
    private DomainsPage domainsPage;
    private static final String NEWSPONSOREDLINKSNUMBER = "3";
    private static final String NEWADSPERCANTAGE = "33.33";
    private static final String DOMAINRAITING = "PG";
    private SoftAssert softAssert = new SoftAssert();

    @Test(groups = {"smoke", "publishing", "widgets"}, priority = 2)
    public void widgetCreationTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetCreating();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        installationCodePage.returnToWidgetsPage();
        Assert.assertTrue(widgetsPage.isWidgetDisplayed(installationCodePage.getWidgetID()), "New widget wasn't created");
    }

    @Test(groups = {"smoke", "publishing", "widgets"}, priority = 2)
    public void widgetDeletionTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetCreating();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        installationCodePage.returnToWidgetsPage();
        widgetsPage.deleteSpecifiedWidget(installationCodePage.getWidgetID());
        Assert.assertFalse(widgetsPage.isWidgetDisplayed(installationCodePage.getWidgetID()), "Specified widget wasn't deleted");
    }

    @Test(groups = {"smoke", "publishing", "widgets"}, priority = 2)
    public void widgetEditingTest() {
        widgetsPage = widgetReportPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetCreating();
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        installationCodePage.returnToWidgetsPage();
        widgetsPage.openSpecifiedWidgetSettings(installationCodePage.getWidgetID());
        widgetSettingsPage.enterSponsoredLinksNumber(NEWSPONSOREDLINKSNUMBER);
        widgetSettingsPage.saveWidgetSettings();
        widgetSettingsPage.backToWidgetsPageAfterSettingsSave();
        Assert.assertTrue(widgetsPage.isAdsPercantageChanged(NEWADSPERCANTAGE, installationCodePage.getWidgetID()), "Ads percantage value wasn't changed");
    }

    @Test(groups = {"smoke", "publishing", "widgets", "domains"}, priority = 3)
    public void domainCreationTest() {
        domainsPage = widgetReportPage.navigateToDomainsPage();
        domainsPage.addNewDomain();
        Assert.assertTrue(domainsPage.isSpecifiedDomainAdded(), "New domain wasn't created");
    }

    @Test(groups = {"smoke", "publishing", "widgets", "domains"}, priority = 3)
    public void domainDeletionTest() {
        domainsPage = widgetReportPage.navigateToDomainsPage();
        domainsPage.addNewDomain();
        domainsPage.deleteSpecifiedDomain();
        Assert.assertFalse(domainsPage.isSpecifiedDomainDeleted(), "Specified domain wasn't deleted");
    }

    @Test(groups = {"smoke", "publishing", "widgets", "domains"}, priority = 3)
    public void domainEditingTest() {
        domainsPage = widgetReportPage.navigateToDomainsPage();
        domainsPage.addNewDomain();
        domainsPage.changeDomainRaiting();
        domainsPage.addBrainExclude();
        softAssert.assertEquals(domainsPage.getDomainDefaultRating(), DOMAINRAITING, "Domain raiting wasn't changed");
        softAssert.assertTrue(domainsPage.isBrainExcludeChecked(), "Brain wasn't excluded from specified domain");
        softAssert.assertAll();
    }
}
