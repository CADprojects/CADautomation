package page.advertising;

import base.ListPageBase;
import base.PageBase;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class BlockListsPage extends ListPageBase {

    private static final By NEWBLOCKLISTBUTTON = get("BlockListsPage.NewBlockListButton");
    private static final By BACKTOALLLISTSBUTTON = get("BlockListsPage.BackToAllBlockLists");

    public BlockListsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpecifiedBlockListCreated() {
        return isSpecifiedListCreated();
    }

    public void deleteSpecifiedBlockList() {
        deleteSpecifiedList();
    }

    public void editSpecifiedBlockList() {
        editSpecifiedList();
    }

    public void returnToAllBlockLists() {
        returnToAllLists(BACKTOALLLISTSBUTTON);
    }

    public BlockListDomainsPage addNewBlockList() {
        addNewList(NEWBLOCKLISTBUTTON);
        return new BlockListDomainsPage(driver);
    }
}
