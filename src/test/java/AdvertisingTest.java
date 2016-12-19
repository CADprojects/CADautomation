import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.advertising.*;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class AdvertisingTest extends TestBase {

    private CampaignsPage campaignsPage;
    private BlockListsPage blockListsPage;
    private CampaignContentPage campaignContentPage;
    private CampaignSettingsPage campaignSettingsPage;
    private BlockListDomainsPage blockListDomainsPage;
    private static final String NEWDESKTOPCPCVALUE = "0.6";
    private static final String NEWMOBILECPCVALUE = "0.5";


    @Test
    public void campaignCreationTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        Assert.assertTrue(campaignsPage.isSpecifiedCampaignDisplayed(), "New campaign wasn't created");
    }

    @Test
    public void campaignDeletionTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        campaignsPage.deleteSpecifiedCampaign();
        Assert.assertFalse(campaignsPage.isSpecifiedCampaignDisplayed(), "Specified campaign wasn't deleted");
    }

    @Test
    public void campaignEditingTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        campaignsPage.openSpecifiedCampaignSettings();
        campaignSettingsPage.addOrRemoveTargetByTablet(); // remove target by tablet
        campaignSettingsPage.addOrRemoveTargetByDesktop(); // add target by desktop
        campaignSettingsPage.addDesktopCPC(NEWDESKTOPCPCVALUE);
        campaignSettingsPage.confirmCPCIncreasing();
        campaignSettingsPage.addMobileCPC(NEWMOBILECPCVALUE);
        campaignSettingsPage.confirmCPCIncreasing();
        campaignSettingsPage.saveChanges();
        Assert.assertTrue(campaignsPage.isCPCValuesChange(NEWDESKTOPCPCVALUE, NEWMOBILECPCVALUE), "Specified campaign CPC values weren't changed");
    }

    @Test
    public void adCreationTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addAd();
        Assert.assertTrue(campaignContentPage.isAdDisplayed(), "New ad wasn't created");
    }

    @Test
    public void adDeletionTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addAd();
        campaignContentPage.deleteAd();
        Assert.assertFalse(campaignContentPage.isAdDisplayed(), "Specified ad wasn't deleted");
    }

    @Test
    public void adEditingTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addAd();
        campaignContentPage.openSpecifiedAdSettings();
        campaignContentPage.changeAdTitle();
        campaignContentPage.saveAdChanges();
        Assert.assertTrue(campaignContentPage.isAdDisplayed(), "Specified ad's title wasn't changed");
    }

    @Test
    public void blockListCreationTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.returnToAllBlockLists();
        Assert.assertTrue(blockListsPage.isSpecifiedBlockListCreated(), "Specified block list wasn't created");
    }

    @Test
    public void blockListDeletionTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.returnToAllBlockLists();
        blockListsPage.deleteSpecifiedBlockList();
        Assert.assertFalse(blockListsPage.isSpecifiedBlockListCreated(), "Specified block list wasn't deleted");
    }

    @Test
    public void blockListEditingTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        campaignsPage = blockListDomainsPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreating(blockListsPage.getBlockListID());
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        campaignsPage.navigateToBlockListsPage();
        blockListsPage.editSpecifiedBlockList();
        blockListsPage.changeCampaignList(campaignsPage.getCampaignID());
        blockListsPage.saveSettings();
        blockListsPage.returnToAllBlockLists();
        Assert.assertTrue(blockListsPage.isCampaignsListEmptyForSpecifiedList(), "Campaign list for specified block list isn't empty");
    }

    @Test
    public void blockListDomainCreationTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.addDomain();
        Assert.assertTrue(blockListDomainsPage.isDomainAdded(), "Domain wasn't added to block list");
    }


    @Test
    public void blockListDomainDeletionTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.addDomain();
        blockListDomainsPage.deleteDomain();
        Assert.assertTrue(blockListDomainsPage.isDomainDeleted(), "Domain wasn't deleted from block list");
    }
}
