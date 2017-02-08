package base;

import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.advertising.BlockListDomainsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by andrei.ostrovski on 08.02.2017.
 */
public class ListPageBase extends PageBase {

    private static final By LISTNAMEINPUT = get("ListPageBase.ListNameInput");
    private static final By SAVELISTSETTINGSBUTTON = get("ListPageBase.SaveButton");
    private static final By CONFIRMLISTDELETIONBUTTON = get("ListPageBase.ConfirmBlockListDeletionButton");
    private static final By SUCCESSFULSAVECHANGESALERT = get("ListPageBase.SuccessfulSaveChangesNotif");
    private static final String SPECIFIEDLISTROW = "input[value='%s']+div";
    private static final String SPECIFIEDLISTCAMPAIGNSLIST = "input[value='%s']+div .campaignInfo>:nth-child(3)";
    private static final String SPECIFIEDLISTEDITBUTTON = "input[value='%s']+div a#lbSettings";
    private static final String SPECIFIEDLISTDELETEBUTTON = "input[value='%s']+div a#lbDelete";
    private static final String SPECIFIEDCAMPAIGNCHECKBOX = "input[value='%s']";
    private static final String LISTNAME = "TestList";
    private String listID;

    public ListPageBase(WebDriver driver) {
        super(driver);
    }

    public String getListID() {
        return listID;
    }

    public void startNewListAdding(By newButton) {
        driver.findElement(newButton).click();
    }

    public void addListName() {
        driver.findElement(LISTNAMEINPUT).clear();
        driver.findElement(LISTNAMEINPUT).sendKeys(LISTNAME + RandomizersUtils.randomPrefix());
    }

    public void saveSettings() {
        driver.findElement(SAVELISTSETTINGSBUTTON).click();
    }

    public void getNewListID() {
        listID = "";
        List<String> currentURLParts = new ArrayList<>(Arrays.asList(driver.getCurrentUrl().split("/")));
        listID = currentURLParts.get(currentURLParts.size()-1);
    }

    public boolean isSpecifiedListCreated() {
        try {
            return driver.findElement(By.cssSelector(String.format(SPECIFIEDLISTROW, listID))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedList() {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDLISTDELETEBUTTON, listID))).click();
            driver.findElement(CONFIRMLISTDELETIONBUTTON).click();
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        } catch (NoSuchElementException ex) {
            System.out.println("Specified list wasn't found");
        }
    }

    public void editSpecifiedList() {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDLISTEDITBUTTON, listID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified list wasn't found");
        }
    }

    public void changeCampaignList(String campaignID) {
        try {
            driver.findElement(By.cssSelector(String.format(SPECIFIEDCAMPAIGNCHECKBOX, campaignID))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified campaign wasn't found");
        }
    }

    public void returnToAllLists(By backToAllListsButton) {
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULSAVECHANGESALERT));
        driver.findElement(backToAllListsButton).click();
    }

    public boolean isCampaignsListEmptyForSpecifiedList() {
        try{
            return driver.findElement(By.cssSelector(String.format(SPECIFIEDLISTCAMPAIGNSLIST, listID))).getText().isEmpty();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified list wasn't found");
            return false;
        }
    }

    public void addNewList(By newButton) {
        startNewListAdding(newButton);
        addListName();
        saveSettings();
        getNewListID();
    }
}
