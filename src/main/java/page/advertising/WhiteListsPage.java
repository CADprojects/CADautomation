package page.advertising;

import base.ListPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by andrei.ostrovski on 08.02.2017.
 */
public class WhiteListsPage extends ListPageBase {

    private static final By NEWWHITELISTBUTTON = get("WhiteListsPage.NewWhiteListButton");
    private static final By BACKTOALLLISTSBUTTON = get("WhiteListsPage.BackToAllWhiteLists");

    public WhiteListsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpecifiedWhiteListCreated() {
        return isSpecifiedListCreated();
    }

    public void deleteSpecifiedWhiteList() {
        deleteSpecifiedList();
    }

    public void editSpecifiedWhiteList() {
        editSpecifiedList();
    }

    public void returnToAllWhiteLists() {
        returnToAllLists(BACKTOALLLISTSBUTTON);
    }

    public WhiteListDomainsPage addNewWhiteList() {
        addNewList(NEWWHITELISTBUTTON);
        return new WhiteListDomainsPage(driver);
    }
}
