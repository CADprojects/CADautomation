package page;

import base.PageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class ContactPage extends PageBase {

    private static final String PAGETITLE = "Contact";

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public boolean isContactPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
