import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
}
