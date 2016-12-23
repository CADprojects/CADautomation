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


    @Test(groups = { "smoke", "advertising", "campaigns"}, priority = 2)
    public void campaignCreationTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        Assert.assertTrue(campaignsPage.isSpecifiedCampaignDisplayed(campaignContentPage.getSourceID()), "New campaign wasn't created");
    }

    @Test(groups = { "smoke", "advertising", "campaigns"}, priority = 2)
    public void campaignDeletionTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        campaignsPage.deleteSpecifiedCampaign(campaignContentPage.getSourceID());
        Assert.assertFalse(campaignsPage.isSpecifiedCampaignDisplayed(campaignContentPage.getSourceID()), "Specified campaign wasn't deleted");
    }

    @Test(groups = { "smoke", "advertising", "campaigns"}, priority = 2)
    public void campaignEditingTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignsPage = campaignContentPage.returnToCampaignsPage();
        campaignsPage.openSpecifiedCampaignSettings(campaignContentPage.getSourceID());
        campaignSettingsPage.addOrRemoveTargetByTablet(); // remove target by tablet
        campaignSettingsPage.addOrRemoveTargetByDesktop(); // add target by desktop
        campaignSettingsPage.addDesktopCPC(NEWDESKTOPCPCVALUE);
        campaignSettingsPage.confirmCPCIncreasing();
        campaignSettingsPage.addMobileCPC(NEWMOBILECPCVALUE);
        campaignSettingsPage.confirmCPCIncreasing();
        campaignSettingsPage.saveChanges();
        Assert.assertTrue(campaignsPage.isCPCValuesChange(NEWDESKTOPCPCVALUE, NEWMOBILECPCVALUE, campaignContentPage.getSourceID()), "Specified campaign CPC values weren't changed");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "ads"}, priority = 3)
    public void adCreationTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addCampaignAd();
        Assert.assertTrue(campaignContentPage.isAdDisplayed(), "New ad wasn't created");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "ads"}, priority = 3)
    public void adDeletionTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addCampaignAd();
        campaignContentPage.deleteAd();
        Assert.assertFalse(campaignContentPage.isAdDisplayed(), "Specified ad wasn't deleted");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "ads"}, priority = 3)
    public void adEditingTest() {
        campaignsPage = widgetReportPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation();
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.addCampaignAd();
        campaignContentPage.openSpecifiedAdSettings();
        campaignContentPage.changeCampaignAdTitle();
        campaignContentPage.saveAdChanges();
        Assert.assertTrue(campaignContentPage.isAdDisplayed(), "Specified ad's title wasn't changed");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "blockList"}, priority = 4)
    public void blockListCreationTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.returnToAllBlockLists();
        Assert.assertTrue(blockListsPage.isSpecifiedBlockListCreated(), "Specified block list wasn't created");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "blockList"}, priority = 4)
    public void blockListDeletionTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.returnToAllBlockLists();
        blockListsPage.deleteSpecifiedBlockList();
        Assert.assertFalse(blockListsPage.isSpecifiedBlockListCreated(), "Specified block list wasn't deleted");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "blockList"}, priority = 4)
    public void blockListEditingTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        campaignsPage = blockListDomainsPage.navigateToCampaignsPage();
        campaignSettingsPage = campaignsPage.startCampaignCreation(blockListsPage.getBlockListID());
        campaignContentPage = campaignSettingsPage.formNewCampaignAndSave();
        campaignContentPage.setSourceID();
        campaignContentPage.navigateToBlockListsPage();
        blockListsPage.editSpecifiedBlockList();
        blockListsPage.changeCampaignList(campaignContentPage.getSourceID());
        blockListsPage.saveSettings();
        blockListsPage.returnToAllBlockLists();
        Assert.assertTrue(blockListsPage.isCampaignsListEmptyForSpecifiedList(), "Campaign list for specified block list isn't empty");
    }

    @Test(groups = { "smoke", "advertising", "campaigns", "blockList"}, priority = 5)
    public void blockListDomainCreationTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.addDomain();
        Assert.assertTrue(blockListDomainsPage.isDomainAdded(), "Domain wasn't added to block list");
    }


    @Test(groups = { "smoke", "advertising", "campaigns", "blockList"}, priority = 5)
    public void blockListDomainDeletionTest() {
        blockListsPage = widgetReportPage.navigateToBlockListsPage();
        blockListDomainsPage = blockListsPage.addNewBlockList();
        blockListDomainsPage.addDomain();
        blockListDomainsPage.deleteDomain();
        Assert.assertTrue(blockListDomainsPage.isDomainDeleted(), "Domain wasn't deleted from block list");
    }
}
