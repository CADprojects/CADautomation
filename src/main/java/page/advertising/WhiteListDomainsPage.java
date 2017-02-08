package page.advertising;

import base.ListDomainsPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by andrei.ostrovski on 08.02.2017.
 */
public class WhiteListDomainsPage extends ListDomainsPageBase {

    private static final By BACKTOALLLISTSBUTTON = get("WhiteListDomainsPage.BackToAllWhiteLists");

    public WhiteListDomainsPage(WebDriver driver) {
        super(driver);
    }

    public void returnToAllWhiteLists() {
        returnToAllLists(BACKTOALLLISTSBUTTON);
    }
}
