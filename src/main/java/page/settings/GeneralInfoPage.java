package page.settings;

import base.PageBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class GeneralInfoPage extends PageBase {

    private static final String PAGETITLE = "General Info";

    public GeneralInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGeneralInfoPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
