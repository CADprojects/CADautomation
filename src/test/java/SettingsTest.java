import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.publishing.InstallationCodePage;
import page.publishing.WidgetSettingsPage;
import page.publishing.WidgetsPage;
import page.settings.*;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class SettingsTest extends TestBase {

    private AutoDepositPage autoDepositPage;
    private PaymentMethodPage paymentMethodPage;
    private EarningsPage earningsPage;
    private CampaignDepositsPage campaignDepositsPage;
    private AddFundsPage addFundsPage;
    private GeneralInfoPage generalInfoPage;
    private TrafficSourcesPage trafficSourcesPage;
    private CustomSourcesPage customSourcesPage;
    private CustomSourceContentPage customSourceContentPage;
    private WidgetsPage widgetsPage;
    private WidgetSettingsPage widgetSettingsPage;
    private InstallationCodePage installationCodePage;
    private SoftAssert softAssert = new SoftAssert();


    @Test
    public void autoDepositLinkTest() {
        autoDepositPage = widgetReportPage.navigateToAutoDepositPage();
        Assert.assertTrue(autoDepositPage.isAutoDepositPageOpened());
    }

    @Test
    public void paymentMethodLinkTest() {
        paymentMethodPage = widgetReportPage.navigateToPaymentMethodPage();
        Assert.assertTrue(paymentMethodPage.isPaymentMethodPageOpened());
    }

    @Test
    public void earningsLinkTest() {
        earningsPage = widgetReportPage.navigateToEarningsPage();
        Assert.assertTrue(earningsPage.isEarningsPageOpened());
    }

    @Test
    public void addFundsLinkTest() {
        campaignDepositsPage = widgetReportPage.navigateToCampaignDepositsPage();
        addFundsPage = campaignDepositsPage.navigateToAddFundsPage();
        Assert.assertTrue(addFundsPage.isAddFundsPageOpened());
    }

    @Test
    public void requestRefundLinkTest() {
        campaignDepositsPage = widgetReportPage.navigateToCampaignDepositsPage();
        campaignDepositsPage.openRequestRefindPopUp();
        Assert.assertTrue(campaignDepositsPage.isRequestRefundDialogOpened());
    }

    @Test
    public void changeEmailTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPage();
        generalInfoPage.changeEmail();
        softAssert.assertTrue(generalInfoPage.isEmailChanged(), "Email wasn't changed");
        generalInfoPage.setDefaultEmail();
        softAssert.assertTrue(generalInfoPage.isEmailDefault(), "Email wasn't changed to default");
        softAssert.assertAll();
    }

    @Test
    public void changePasswordTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPage();
        generalInfoPage.changePassword();
        generalInfoPage.logOut();
        logInPage.logInWithNewPassword(generalInfoPage.getNewPassword());
        softAssert.assertTrue(widgetReportPage.isLogInSuccessful(), "Password wasn't changed");
        widgetReportPage.navigateToGeneralInfoPage();
        generalInfoPage.setDefaultPassword();
        generalInfoPage.logOut();
        logInPage.logIn();
        softAssert.assertTrue(widgetReportPage.isLogInSuccessful(), "Password wasn't changed to default");
        softAssert.assertAll();
    }

    @Test
    public void changeToPublisherOnlyTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPage();
        generalInfoPage.addRemoveAdvertiserFunctions(); // remove here
        softAssert.assertFalse(generalInfoPage.isAdvertisingTabDisplayed(), "Account type is still include advertiser");
        generalInfoPage.addRemoveAdvertiserFunctions(); // add here
        softAssert.assertTrue(generalInfoPage.isAdvertisingTabDisplayed(), "Account type include only publisher");
        softAssert.assertAll();
    }

    @Test
    public void changeToAdvertiserOnlyTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPage();
        generalInfoPage.addRemovePublisherFunctions(); // remove here
        softAssert.assertFalse(generalInfoPage.isPublishingTabDisplayed(), "Account type is still include publisher");
        generalInfoPage.addRemovePublisherFunctions(); // add here
        softAssert.assertTrue(generalInfoPage.isPublishingTabDisplayed(), "Account type include only advertiser");
        softAssert.assertAll();
    }

    @Test
    public void addSourceValue() {
        trafficSourcesPage = widgetReportPage.navigateToTrafficSourcesPage();
        trafficSourcesPage.addNewSourceValue();
        Assert.assertTrue(trafficSourcesPage.isAddedSourceValueDisplayed(), "New source value wasn't added");
    }

    @Test
    public void removeSourceValue() {
        trafficSourcesPage = widgetReportPage.navigateToTrafficSourcesPage();
        trafficSourcesPage.addNewSourceValue();
        trafficSourcesPage.deleteSpecifiedSourceValue();
        Assert.assertFalse(trafficSourcesPage.isAddedSourceValueDisplayed(), "Specified source value wasn't deleted");
    }

    @Test
    public void addCampaignValue() {
        trafficSourcesPage = widgetReportPage.navigateToTrafficSourcesPage();
        trafficSourcesPage.addNewCampaignValue();
        Assert.assertTrue(trafficSourcesPage.isAddedCampaignValueDisplayed(), "New campaign value wasn't added");
    }

    @Test
    public void removeCampaignValue() {
        trafficSourcesPage = widgetReportPage.navigateToTrafficSourcesPage();
        trafficSourcesPage.addNewCampaignValue();
        trafficSourcesPage.deleteSpecifiedCampaignValue();
        Assert.assertFalse(trafficSourcesPage.isAddedCampaignValueDisplayed(), "Specified campaign value wasn't deleted");
    }

    @Test
    public void customSourceCreationTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        Assert.assertTrue(customSourcesPage.isSpecifiedSuctomSourceDisplayed(), "New custom source wasn't created");
    }

    @Test
    public void customSourceDeletionTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        customSourcesPage.deleteSpecifiedSuctomSource();
        Assert.assertFalse(customSourcesPage.isSpecifiedSuctomSourceDisplayed(), "Specified custom source wasn't deleted");
    }

    @Test
    public void customSourceEditingTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        customSourceContentPage = customSourcesPage.openSourceContentPage();
        customSourceContentPage.setSourceID();
        widgetsPage = customSourceContentPage.navigateToWidgetsPage();
        widgetSettingsPage = widgetsPage.startWidgetCreating(customSourceContentPage.getSourceID());
        installationCodePage = widgetSettingsPage.formNewWidgetAndSave();
        installationCodePage.setWidgetId();
        installationCodePage.navigateToCustomSourcesPage();
        customSourcesPage.openSpecifiedSuctomSourceSettings();
        customSourcesPage.addRemoveSpecifiedServeWidget(installationCodePage.getWidgetID()); // remove here
        customSourcesPage.saveCustomSourceSettingsChanges();
        Assert.assertTrue(customSourcesPage.isWidgetsListEmptyForSpecifiedCustomSource(), "Widgets list for specified custom source isn't empty ");
    }

    @Test
    public void adForCustomSourceCreationTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        customSourceContentPage = customSourcesPage.openSourceContentPage();
        customSourceContentPage.addCustomSourceAd();
        Assert.assertTrue(customSourceContentPage.isAdDisplayed(), "New ad wasn't created");
    }

    @Test
    public void adForCustomSourceDeletionTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        customSourceContentPage = customSourcesPage.openSourceContentPage();
        customSourceContentPage.addCustomSourceAd();
        customSourceContentPage.deleteAd();
        Assert.assertFalse(customSourceContentPage.isAdDisplayed(), "Specified ad wasn't deleted");
    }

    @Test
    public void adForCustomSourceEditingTest() {
        customSourcesPage = widgetReportPage.navigateToCustomSourcesPage();
        customSourcesPage.startCustomSourceCreation();
        customSourcesPage.addNewCustomSource();
        customSourceContentPage = customSourcesPage.openSourceContentPage();
        customSourceContentPage.addCustomSourceAd();
        customSourceContentPage.openSpecifiedAdSettings();
        customSourceContentPage.changeCustomSourceAdTitle();
        customSourceContentPage.saveAdChanges();
        Assert.assertTrue(customSourceContentPage.isAdDisplayed(), "Specified ad's title wasn't changed");
    }
}
