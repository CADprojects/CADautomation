package page.advertising;

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
public class BlockListDomainsPage extends PageBase {

    private static final By BACKTOALLLISTSBUTTON = get("BlockListDomainsPage.BackToAllBlockLists");
    private static final By ADDDOMAININPUT = get("BlockListDomainsPage.AddDomainInput");
    private static final By ADDDOMAINSBUTTON = get("BlockListDomainsPage.AddDomainsButton");
    private static final By REMOVEDOMAINSBUTTON = get("BlockListDomainsPage.RemoveDomainsButton");
    private static final By ADDORREMOVEDOMAINSPOPUPSAVEBUTTON = get("BlockListDomainsPage.AddDomainsSaveButton");
    private static final By SUCCESSFULDOMAINSADDORREMOVEALERT = get("BlockListDomainsPage.SuccessfulDomainsChangesSaveNotif");
    private static final By DOMAINIDFIELD = get("BlockListDomainsPage.DomainIDField");
    private static final By NODOMAINSFILED = get("BlockListDomainsPage.NoDomainsField");
    private static final String SPECIFIEDDOMAINDELETEBUTTON = "//table[@id='DomainsGrid']//td[text()='%s']/..//a";
    private static final String DOMAIN = "3456";

    public BlockListDomainsPage(WebDriver driver) {
        super(driver);
    }

    public void returnToAllBlockLists() {
        driver.findElement(BACKTOALLLISTSBUTTON).click();
    }

    public void addDomain() {
        driver.findElement(ADDDOMAININPUT).clear();
        driver.findElement(ADDDOMAININPUT).sendKeys(DOMAIN);
        driver.findElement(ADDDOMAINSBUTTON).click();
        driver.findElement(ADDORREMOVEDOMAINSPOPUPSAVEBUTTON).click();
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULDOMAINSADDORREMOVEALERT));
        WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULDOMAINSADDORREMOVEALERT));
        driver.navigate().refresh();
    }

    public void deleteDomain() {
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINDELETEBUTTON, DOMAIN))).click();
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFULDOMAINSADDORREMOVEALERT));
            WaitersUtils.getWaiter(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(SUCCESSFULDOMAINSADDORREMOVEALERT));
            driver.navigate().refresh();
        } catch (NoSuchElementException ex) {
            System.out.println("Specifed domain wasn't found");
        }
    }

    public boolean isDomainAdded() {
        try {
            return driver.findElement(DOMAINIDFIELD).getText().contentEquals(DOMAIN);
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isDomainDeleted() {
        try {
            return driver.findElement(NODOMAINSFILED).isDisplayed();
        } catch (NoSuchElementException ex) {
            System.out.println("Block list still has domains");
            return false;
        }
    }
}
