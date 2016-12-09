package page.settings;

import base.PageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class PaymentMethodPage extends PageBase {

    private static final String PAGETITLE = "Payment Method";

    public PaymentMethodPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPaymentMethodPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
