package page.settings;

import base.PageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class AutoDepositPage extends PageBase {

    private static final String PAGETITLE = "Auto Deposits";

    public AutoDepositPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAutoDepositPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
