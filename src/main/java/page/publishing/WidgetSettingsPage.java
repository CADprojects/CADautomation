package page.publishing;

import base.PageBase;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 12.12.2016.
 */
public class WidgetSettingsPage extends PageBase {

    private static final By WIDGETNAMEINPUT = get("WidgetSettingsPage.WidgetNameInput");
    private static final By ADDNEWDOMAINDLINK = get("WidgetSettingsPage.AddDomainLink");
    private static final By DOMAINURLINPUT = get("WidgetSettingsPage.DomainURLInput");
    private static final By SAVENEWDOMAINBUTTON = get("WidgetSettingsPage.SaveNewDomainButton");
    private static final By TEXTONLYWTYPEBUTTON = get("WidgetSettingsPage.TextOnlyWTypeButton");
    private static final By FIXEDLAYOUTTAB = get("WidgetSettingsPage.FixedLayoutTab");
    private static final By CONTENTLINKSINPUT = get("WidgetSettingsPage.ContentLinksInput");
    private static final By SPONSOREDCONTENTINPUT = get("WidgetSettingsPage.SponsoredContentInput");
    private static final By CONTENTTYPEPGBUTTON = get("WidgetSettingsPage.ContentTypePGButton");
    private static final By CONFIRMCONTENTTYPEYESBUTTON = get("WidgetSettingsPage.ConfirmContentTypeYesButton");
    private static final By INTCONTENTFIRSTNOBUTTON = get("WidgetSettingsPage.InternalContentFirstNoButton");
    private static final By TITLELENGTHDROPDOWN = get("WidgetSettingsPage.TitleLengthDropdown");
    private static final By DOMAINREQUESTAPPROVALCHECKBOX = get("WidgetSettingsPage.DomainRequestApprovalCheckbox");
    private static final By CPCMINBYIMPRADIOBUTTON = get("WidgetSettingsPage.CPCMinByImpNumberRadioButton");
    private static final By CPCBYIMPTABLE = get("WidgetSettingsPage.CPCByImpTable");
    private static final By DISPLAYSTYLESTORIESBUTTON = get("WidgetSettingsPage.DisplayStyleStoriesButton");
    private static final By SAVEBUTTON = get("WidgetSettingsPage.SaveButton");
    private static final By SUCCESSFULLSAVINGNOTIF = get("WidgetSettingsPage.SuccessfulSavingNotif");
    private static final By BACKTOWIDGETSLINK = get("WidgetSettingsPage.BackToWidgetLink");
    private static final By AUTOCONTENTRADIOBUTTON = get("WidgetSettingsPage.AutomaticallyFindContentRadioButton");
    private static final By CUSTOMSOURCERADIOBUTTON = get("WidgetSettingsPage.CustomSourceRadioButton");
    private static final By CUSTOMSOURCEDROPDOWN = get("WidgetSettingsPage.CustomSourceDropdown");
    private static final String CONTENTLINKSNUMBER = "9";
    private static final String SPONSOREDCONTENTNUMBER = "4";
    private static final String TITLELENGTHVALUE = "50";
    private String customSourceID;

    public WidgetSettingsPage(WebDriver driver) {
        super(driver);
    }

    public WidgetSettingsPage(WebDriver driver, String customSourceID) {
        super(driver);
        this.customSourceID = customSourceID;
    }

    public void addWidgetName() {
        driver.findElement(WIDGETNAMEINPUT).clear();
        driver.findElement(WIDGETNAMEINPUT).sendKeys("TestWidget"+ RandomizersUtils.randomPrefix());
    }

    public void addWidgetDomain() {
        driver.findElement(ADDNEWDOMAINDLINK).click();
        driver.findElement(DOMAINURLINPUT).clear();
        driver.findElement(DOMAINURLINPUT).sendKeys(String.format("http://testdomain%s.com",RandomizersUtils.randomPrefix()));
        driver.findElement(SAVENEWDOMAINBUTTON).click();
    }

    public void chooseTextOnlyWType() {
        driver.findElement(TEXTONLYWTYPEBUTTON).click();
    }

    public void chooseFixedLayout() {
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(DISPLAYSTYLESTORIESBUTTON));
        driver.findElement(FIXEDLAYOUTTAB).click();

    }

    public void enterContentLinksNumber(String linksNumber) {
        driver.findElement(CONTENTLINKSINPUT).sendKeys(Keys.BACK_SPACE);
        driver.findElement(CONTENTLINKSINPUT).sendKeys(linksNumber);
    }

    public void enterSponsoredLinksNumber(String linksNumber) {
        driver.findElement(SPONSOREDCONTENTINPUT).sendKeys(Keys.BACK_SPACE);
        driver.findElement(SPONSOREDCONTENTINPUT).sendKeys(linksNumber);
    }

    public void chooseAdvancedOptions() {
        driver.findElement(CONTENTTYPEPGBUTTON).click();
        driver.findElement(CONFIRMCONTENTTYPEYESBUTTON).click();
        driver.findElement(INTCONTENTFIRSTNOBUTTON).click();
        Select titleLengthDropdown = new Select(driver.findElement(TITLELENGTHDROPDOWN));
        titleLengthDropdown.selectByValue(TITLELENGTHVALUE);
        driver.findElement(CPCMINBYIMPRADIOBUTTON).click();
        List<WebElement> cpcMinimumTable = driver.findElements(CPCBYIMPTABLE);
        for (WebElement cell: cpcMinimumTable) {
            cell.clear();
            cell.sendKeys("0.1");
        }
    }

    public void setInternalContentSource() {
        if (customSourceID == null) {
            driver.findElement(AUTOCONTENTRADIOBUTTON).click();
        } else {
            driver.findElement(CUSTOMSOURCERADIOBUTTON).click();
            Select customSources = new Select(driver.findElement(CUSTOMSOURCEDROPDOWN));
            customSources.selectByValue(customSourceID);
        }
    }

    public void checkDomainApprovalRequest() {
        driver.findElement(DOMAINREQUESTAPPROVALCHECKBOX).click();
    }

    public void saveWidgetSettings() {
        driver.findElement(SAVEBUTTON).click();
    }

    public InstallationCodePage formNewWidgetAndSave() {
        addWidgetName();
        addWidgetDomain();
        chooseTextOnlyWType();
        chooseFixedLayout();
        enterContentLinksNumber(CONTENTLINKSNUMBER);
        enterSponsoredLinksNumber(SPONSOREDCONTENTNUMBER);
        chooseAdvancedOptions();
        setInternalContentSource();
        checkDomainApprovalRequest();
        saveWidgetSettings();
        return new InstallationCodePage(driver);
    }

    public void backToWidgetsPageAfterSettingsSave() {
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULLSAVINGNOTIF));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULLSAVINGNOTIF));
        driver.findElement(BACKTOWIDGETSLINK).click();
    }
}
