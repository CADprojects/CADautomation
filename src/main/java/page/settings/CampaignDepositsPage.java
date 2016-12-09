package page.settings;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class CampaignDepositsPage extends PageBase {

    private static final By ADDFUNDSLINK = get("CampaignDepositsPage.AddFundsLink");
    private static final By REQUESTREFUNDLINK = get("CampaignDepositsPage.RequestRefundLink");
    private static final By REQUESTREFUNDDIALOGHEADER = get("CampaignDepositsPage.RequestRefundDialogHeader");
    private static final String REQUESTREFUNDPOPUPTITLE = "Request Refund";

    public CampaignDepositsPage(WebDriver driver) {
        super(driver);
    }

    public AddFundsPage navigateToAddFundsPage() {
        driver.findElement(ADDFUNDSLINK).click();
        return new AddFundsPage(driver);
    }

    public void openRequestRefindPopUp() {
        driver.findElement(REQUESTREFUNDLINK).click();
    }

    public boolean isRequestRefundDialogOpened() {
        return driver.findElement(REQUESTREFUNDDIALOGHEADER).getText().contentEquals(REQUESTREFUNDPOPUPTITLE);
    }
}
