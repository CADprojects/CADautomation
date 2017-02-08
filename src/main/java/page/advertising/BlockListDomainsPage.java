package page.advertising;

import base.ListDomainsPageBase;
import base.PageBase;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 19.12.2016.
 */
public class BlockListDomainsPage extends ListDomainsPageBase {

    private static final By BACKTOALLLISTSBUTTON = get("BlockListDomainsPage.BackToAllBlockLists");

    public BlockListDomainsPage(WebDriver driver) {
        super(driver);
    }

    public void returnToAllBlockLists() {
        returnToAllLists(BACKTOALLLISTSBUTTON);
    }
}
