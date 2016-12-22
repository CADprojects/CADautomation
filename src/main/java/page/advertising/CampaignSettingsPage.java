package page.advertising;

import base.PageBase;
import helper.RandomizersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 15.12.2016.
 */
public class CampaignSettingsPage extends PageBase {

    private static final By TITLEONPAGE = get("CampaignSettingsPage.TitleOnPage");
    private static final By CAMPAIGNNAMEINPUT = get("CampaignSettingsPage.CampaignNameInput");
    private static final By MANUALLYBUTTON = get("CampaignSettingsPage.ManuallyButton");
    private static final By NONCOMMERCIALBUTTON = get("CampaignSettingsPage.NoncommercialButton");
    private static final By CPCINPUT = get("CampaignSettingsPage.CPCInput");
    private static final By SPENDPERDAYINPUT = get("CampaignSettingsPage.SpendPerDayInput");
    private static final By SPENDOVERALLINPUT = get("CampaignSettingsPage.SpendOverallInput");
    private static final By SPECIFICREGIONBUTTON = get("CampaignSettingsPage.SpecificRegionsRadioButton");
    private static final By CANADACHECKBOX = get("CampaignSettingsPage.CanadaCheckbox");
    private static final By FRANCECHECKBOX = get("CampaignSettingsPage.FranceCheckbox");
    private static final By GERMANYCHECKBOX = get("CampaignSettingsPage.GermanyCheckbox");
    private static final By SPECIFICSTATESBUTTON = get("CampaignSettingsPage.SpecificStatesRadioButton");
    private static final By SELECTSTATESBUTTON = get("CampaignSettingsPage.SelectStatesButton");
    private static final By ALLSTATESCHECKBOXES = get("CampaignSettingsPage.AllStatesCheckboxes");
    private static final By SELECTOSBUTTON = get("CampaignSettingsPage.SelectOSButton");
    private static final By SELECTALLOSSBUTTON = get("CampaignSettingsPage.SelectAllOSsButton");
    private static final By BLOCKLISTSFROPDOWN = get("CampaignSettingsPage.BlockListsDropdown");
    private static final By TARGETDEVICESCHECKBOX = get("CampaignSettingsPage.TargetByDeviceCheckbox");
    private static final By TARGETDESKTOPCHECKBOX = get("CampaignSettingsPage.TargetDesktopCheckbox");
    private static final By TARGETTABLETCHECKBOX = get("CampaignSettingsPage.TargetTabletCheckbox");
    private static final By MOBILECPCINPUT = get("CampaignSettingsPage.MobileCPCInput");
    private static final By DESKTOPCPCINPUT = get("CampaignSettingsPage.DesktopCPCInput");
    private static final By TABLETCPCINPUT = get("CampaignSettingsPage.TabletCPCInput");
    private static final By CPCINCREASEOKBUTTON = get("CampaignSettingsPage.LargeCPCIncreaseOKButton");
    private static final By SAVEBUTTON = get("CampaignSettingsPage.SaveButton");
    private static final String CAMPAIGNNAMEMASK = "TestCampaign";
    private static final String CPC = "0.3";
    private static final String SPENDPERDAY = "60";
    private static final String SPENDOVERALL = "1000";
    private static final String DESKTOPCPC = "0.1";
    private static final String MOBILECPC = "0.2";
    private static final String TABLETCPC = "0.3";
    private String blockListID;

    public CampaignSettingsPage(WebDriver driver) {
        super(driver);
    }

    public CampaignSettingsPage(WebDriver driver, String blockListID) {
        super(driver);
        this.blockListID = blockListID;
    }

    public void addCampaignName() {
        driver.findElement(CAMPAIGNNAMEINPUT).clear();
        driver.findElement(CAMPAIGNNAMEINPUT).sendKeys(CAMPAIGNNAMEMASK + RandomizersUtils.randomPrefix());
    }

    public void addCPC(String cpcValue) {
        driver.findElement(CPCINPUT).clear();
        driver.findElement(CPCINPUT).sendKeys(cpcValue);
    }

    public void addSpendPerDay(String spendPerDayValue) {
        driver.findElement(SPENDPERDAYINPUT).clear();
        driver.findElement(SPENDPERDAYINPUT).sendKeys(spendPerDayValue);
    }

    public void addSpendOverall(String spendOverallValue) {
        driver.findElement(SPENDOVERALLINPUT).clear();
        driver.findElement(SPENDOVERALLINPUT).sendKeys(spendOverallValue);
    }

    public void specifyCountryTargeting() {
        driver.findElement(SPECIFICREGIONBUTTON).click();
        driver.findElement(CANADACHECKBOX).click();
        driver.findElement(FRANCECHECKBOX).click();
        driver.findElement(GERMANYCHECKBOX).click();
    }

    public void specifyStateTargeting() {
        driver.findElement(SPECIFICSTATESBUTTON).click();
        driver.findElement(SELECTSTATESBUTTON).click();
        List<WebElement> allStatesCheckboxes = new ArrayList<>(driver.findElements(ALLSTATESCHECKBOXES));
        for (int i = 0;i <3; i++) {
            allStatesCheckboxes.get(i).click();
        }
    }

    public void specifyOSTargeting() {
        driver.findElement(SELECTOSBUTTON).click();
        driver.findElement(SELECTALLOSSBUTTON).click();
    }

    public void assignBlockList(String blockListID) {
        if (blockListID != null) {
            Select blockList =  new Select(driver.findElement(BLOCKLISTSFROPDOWN));
            blockList.selectByValue(blockListID);
        }
    }

    public void addTargetByDeviceOption() {
        driver.findElement(TARGETDEVICESCHECKBOX).click();
    }

    public void addOrRemoveTargetByDesktop() {
        driver.findElement(TARGETDESKTOPCHECKBOX).click();
    }

    public void addOrRemoveTargetByTablet() {
        driver.findElement(TARGETTABLETCHECKBOX).click();
    }

    public void addDesktopCPC(String cpcValue) {
        driver.findElement(DESKTOPCPCINPUT).clear();
        driver.findElement(DESKTOPCPCINPUT).sendKeys(cpcValue);
    }

    public void addMobileCPC(String cpcValue) {
        driver.findElement(MOBILECPCINPUT).clear();
        driver.findElement(MOBILECPCINPUT).sendKeys(cpcValue);
    }

    public void addTabletCPC(String cpcValue) {
        driver.findElement(TABLETCPCINPUT).clear();
        driver.findElement(TABLETCPCINPUT).sendKeys(cpcValue);
    }

    public void confirmCPCIncreasing() {
        driver.findElement(TITLEONPAGE).click();
        driver.findElement(CPCINCREASEOKBUTTON).click();
    }

    public void saveChanges() {
        driver.findElement(SAVEBUTTON).click();
    }

    public CampaignContentPage formNewCampaignAndSave() {
        addCampaignName();
        driver.findElement(MANUALLYBUTTON).click();
        driver.findElement(NONCOMMERCIALBUTTON).click();
        addCPC(CPC);
        addSpendPerDay(SPENDPERDAY);
        addSpendOverall(SPENDOVERALL);
        specifyCountryTargeting();
        specifyStateTargeting();
        specifyOSTargeting();
        assignBlockList(blockListID);
        addTargetByDeviceOption();
        addDesktopCPC(DESKTOPCPC);
        addMobileCPC(MOBILECPC);
        addTabletCPC(TABLETCPC);
        addOrRemoveTargetByDesktop(); // remove in this case
        saveChanges();
        return new CampaignContentPage(driver);
    }
}
