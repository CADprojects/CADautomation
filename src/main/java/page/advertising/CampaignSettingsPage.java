package page.advertising;

import base.PageBase;
import helper.RandomizersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 15.12.2016.
 */
public class CampaignSettingsPage extends PageBase {

    private static final By TITLEONPAGE = get("CampaignSettingsPage.TitleOnPage");
    private static final By CAMPAIGNNAMEINPUT = get("CampaignSettingsPage.CampaignNameInput");
    private static final By MANUALLYBUTTON = get("CampaignSettingsPage.ManuallyButton");
    private static final By NONCOMMERCIALBUTTON = get("CampaignSettingsPage.NoncommercialButton");
    private static final By SPENDPERDAYINPUT = get("CampaignSettingsPage.SpendPerDayInput");
    private static final By SPENDOVERALLINPUT = get("CampaignSettingsPage.SpendOverallInput");
    private static final By SPECIFICREGIONBUTTON = get("CampaignSettingsPage.SpecificRegionsRadioButton");
    private static final By SELECTCOUNTRIESLIST = get("CampaignSettingsPage.SelectCountiresList");
    private static final By CANADACHECKBOX = get("CampaignSettingsPage.CanadaCheckbox");
    private static final By FRANCECHECKBOX = get("CampaignSettingsPage.FranceCheckbox");
    private static final By GERMANYCHECKBOX = get("CampaignSettingsPage.GermanyCheckbox");
    private static final By SPECIFICSTATESBUTTON = get("CampaignSettingsPage.SpecificStatesRadioButton");
    private static final By SELECTSTATESBUTTON = get("CampaignSettingsPage.SelectStatesList");
    private static final By SPECIFICOSCHECKBOX = get("CampaignSettingsPage.SpecificOSsCheckbox");
    private static final By SELECTOSBUTTON = get("CampaignSettingsPage.SelectOSList");
    private static final By SELECTALLBUTTON = get("CampaignSettingsPage.SelectAllButton");
    private static final By BLOCKLISTSFROPDOWN = get("CampaignSettingsPage.BlockListsDropdown");
    private static final By WHITELISTSFROPDOWN = get("CampaignSettingsPage.WhiteListsDropdown");
    private static final By TARGETDESKTOPCHECKBOX = get("CampaignSettingsPage.TargetDesktopCheckbox");
    private static final By TARGETTABLETCHECKBOX = get("CampaignSettingsPage.TargetTabletCheckbox");
    private static final By MOBILECPCINPUT = get("CampaignSettingsPage.MobileCPCInput");
    private static final By DESKTOPCPCINPUT = get("CampaignSettingsPage.DesktopCPCInput");
    private static final By TABLETCPCINPUT = get("CampaignSettingsPage.TabletCPCInput");
    private static final By CPCINCREASEOKBUTTON = get("CampaignSettingsPage.LargeCPCIncreaseOKButton");
    private static final By SAVEBUTTON = get("CampaignSettingsPage.SaveButton");
    private static final String CAMPAIGNNAMEMASK = "TestCampaign";
    private static final String SPENDPERDAY = "60";
    private static final String SPENDOVERALL = "1000";
    private static final String DESKTOPCPC = "0.1";
    private static final String MOBILECPC = "0.2";
    private static final String TABLETCPC = "0.3";

    public CampaignSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void addCampaignName() {
        driver.findElement(CAMPAIGNNAMEINPUT).clear();
        driver.findElement(CAMPAIGNNAMEINPUT).sendKeys(CAMPAIGNNAMEMASK + RandomizersUtils.randomPrefix());
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
        driver.findElement(SELECTCOUNTRIESLIST).click();
        driver.findElement(CANADACHECKBOX).click();
        driver.findElement(FRANCECHECKBOX).click();
        driver.findElement(GERMANYCHECKBOX).click();
    }

    public void specifyStateTargeting() {
        driver.findElement(SPECIFICSTATESBUTTON).click();
        driver.findElement(SELECTSTATESBUTTON).click();
        driver.findElement(SELECTALLBUTTON).click();
    }

    public void specifyOSTargeting() {
        driver.findElement(SPECIFICOSCHECKBOX).click();
        driver.findElement(SELECTOSBUTTON).click();
        driver.findElement(SELECTALLBUTTON).click();
    }

    public void assignBlockList(String blockListID) {
        if (!blockListID.isEmpty()) {
            Select blockList =  new Select(driver.findElement(BLOCKLISTSFROPDOWN));
            blockList.selectByValue(blockListID);
        }
    }

    public void assignWhiteList(String whiteListID) {
        if (!whiteListID.isEmpty()) {
            Select blockList =  new Select(driver.findElement(WHITELISTSFROPDOWN));
            blockList.selectByValue(whiteListID);
        }
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
        addSpendPerDay(SPENDPERDAY);
        addSpendOverall(SPENDOVERALL);
        specifyCountryTargeting();
        specifyStateTargeting();
        specifyOSTargeting();
        addDesktopCPC(DESKTOPCPC);
        addMobileCPC(MOBILECPC);
        addTabletCPC(TABLETCPC);
        addOrRemoveTargetByDesktop(); // remove in this case
        saveChanges();
        return new CampaignContentPage(driver);
    }

    public CampaignContentPage formNewCampaignAndSave(String blocklistID, String whiteListID) {
        addCampaignName();
        driver.findElement(MANUALLYBUTTON).click();
        driver.findElement(NONCOMMERCIALBUTTON).click();
        addSpendPerDay(SPENDPERDAY);
        addSpendOverall(SPENDOVERALL);
        specifyCountryTargeting();
        specifyStateTargeting();
        specifyOSTargeting();
        assignBlockList(blocklistID);
        assignWhiteList(whiteListID);
        addDesktopCPC(DESKTOPCPC);
        addMobileCPC(MOBILECPC);
        addTabletCPC(TABLETCPC);
        addOrRemoveTargetByDesktop(); // remove in this case
        saveChanges();
        return new CampaignContentPage(driver);
    }
}
