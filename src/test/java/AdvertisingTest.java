import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.advertising.AdsPage;
import page.advertising.BlockListsPage;
import page.advertising.CampaignSettingsPage;
import page.advertising.CampaignsPage;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class AdvertisingTest extends TestBase {

    private CampaignsPage campaignsPage;
    private BlockListsPage blockListsPage;
    private AdsPage adsPage;
    private CampaignSettingsPage campaignSettingsPage;

    @Test
    public void campaignCreationTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        adsPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = adsPage.returnToCampaignsPage();
        Assert.assertTrue(campaignsPage.isSpecifiedCampaignDisplayed(), "New campaign wasn't created");
    }

    @Test
    public void campaignDeletionTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        adsPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = adsPage.returnToCampaignsPage();
        campaignsPage.deleteSpecifiedCampaign();
        Assert.assertFalse(campaignsPage.isSpecifiedCampaignDisplayed(), "Specified campaign wasn't deleted");
    }
}
