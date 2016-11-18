package base;

import org.openqa.selenium.WebDriver;
import page.part.NavigationMenu;
import page.part.TopMenu;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class PageBase {

    protected final NavigationMenu NAVIGATIONMENU;
    protected final TopMenu TOPMENU;
    protected static WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        NAVIGATIONMENU = new NavigationMenu(driver);
        TOPMENU = new TopMenu(driver);
    }
}
