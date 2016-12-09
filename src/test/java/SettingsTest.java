import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
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

}
