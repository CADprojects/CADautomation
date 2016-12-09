package page.settings;

import base.PageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class EarningsPage extends PageBase {

    private static final String PAGETITLE = "Earnings";

    public EarningsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEarningsPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
