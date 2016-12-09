package page;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class HelpHomePage {

    private WebDriver driver;
    private static final String PAGETITLE = "Content.ad Help | Help & Support Home";

    public HelpHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHelpHomePageOpened() {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        return driver.getTitle().contentEquals(PAGETITLE);
    }
}
