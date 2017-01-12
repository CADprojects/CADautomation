package page.advertising;

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
public class BlockListsPage extends PageBase {

    private static final By NEWBLOCKLISTBUTTON = get("BlockListsPage.NewBlockListButton");
    private static final By BLOCKLISTNAMEINPUT = get("BlockListsPage.ListNameInput");
    private static final By SAVEBLOCKLISTSETTINGSBUTTON = get("BlockListsPage.SaveButton");
    private static final By CONFIRMBLOCKLISTDELETIONBUTTON = get("BlockListsPage.ConfirmBlockListDeletionButton");
    private static final By SUCCESSFULSAVECHANGESALERT = get("BlockListsPage.SuccessfulSaveChangesNotif");
    private static final By BACKTOALLLISTSBUTTON = get("BlockListsPage.BackToAllBlockLists");
    private static final String SPECIFIEDBLOCKLISTROW = "input[value='%s']+div";
    private static final String SPECIFIEDBLOCKLISTCAMPAIGNSLIST = "input[value='%s']+div .campaignInfo>:nth-child(3)";
    private static final String SPECIFIEDBLOCKLISTEDITBUTTON = "input[value='%s']+div a#lbSettings";
    private static final String SPECIFIEDBLOCKLISTDELETEBUTTON = "input[value='%s']+div a#lbDelete";
    private static final String SPECIFIEDCAMPAIGNCHECKBOX = "input[value='%s']";
    private static final String LISTNAME = "TestBlockList";
    private String blockListID;

    public BlockListsPage(WebDriver driver) {
        super(driver);
    }

    public String getBlockListID() {
        return blockListID;
    }

    public void startNewBlockListAdding() {
        driver.findElement(NEWBLOCKLISTBUTTON).click();
    }

    public void addListName() {
        driver.findElement(BLOCKLISTNAMEINPUT).clear();
        driver.findElement(BLOCKLISTNAMEINPUT).sendKeys(LISTNAME + RandomizersUtils.randomPrefix());
    }

    public void saveSettings() {
        driver.findElement(SAVEBLOCKLISTSETTINGSBUTTON).click();
    }

    public void getNewBlockListID() {
        List<String> currentURLParts = new ArrayList<>(Arrays.asList(driver.getCurrentUrl().split("/")));
        blockListID = currentURLParts.get(currentURLParts.size()-1);
    }

    public boolean isSpecifiedBlockListCreated() {
        try {
            return driver.findElement(By.cssSelector(String.format(SPECIFIEDBLOCKLISTROW, blockListID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedBlockList() {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDBLOCKLISTDELETEBUTTON, blockListID))).click();
            driver.findElement(CONFIRMBLOCKLISTDELETIONBUTTON).click();
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified block list wasn't found");
        }
    }

    public void editSpecifiedBlockList() {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDBLOCKLISTEDITBUTTON, blockListID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified block list wasn't found");
        }
    }

    public void changeCampaignList(String campaignID) {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDCAMPAIGNCHECKBOX, campaignID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }

    public void returnToAllBlockLists() {
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        driver.findElement(BACKTOALLLISTSBUTTON).click();
    }

    public boolean isCampaignsListEmptyForSpecifiedList() {
        try{
            return driver.findElement(By.cssSelector(String.format(SPECIFIEDBLOCKLISTCAMPAIGNSLIST, blockListID))).getText().isEmpty();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified block list wasn't found");
            return false;
        }
    }

    public BlockListDomainsPage addNewBlockList() {
        startNewBlockListAdding();
        addListName();
        saveSettings();
        getNewBlockListID();
        return new BlockListDomainsPage(driver);
    }
}
