package page.settings;

import base.PageBase;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei.Ostrovski on 09.12.2016.
 */
public class AddFundsPage extends PageBase {

    private static final String PAGETITLE = "Add Funds";

    public AddFundsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAddFundsPageOpened() {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
