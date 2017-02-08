package base;

import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by andrei.ostrovski on 08.02.2017.
 */
public class ListDomainsPageBase extends PageBase {

    private static final By ADDDOMAININPUT = get("ListDomainsPageBase.AddDomainInput");
    private static final By ADDDOMAINSBUTTON = get("ListDomainsPageBase.AddDomainsButton");
    private static final By REMOVEDOMAINSBUTTON = get("ListDomainsPageBase.RemoveDomainsButton");
    private static final By ADDORREMOVEDOMAINSPOPUPSAVEBUTTON = get("ListDomainsPageBase.AddDomainsSaveButton");
    private static final By SUCCESSFULDOMAINSADDORREMOVEALERT = get("ListDomainsPageBase.SuccessfulDomainsChangesSaveNotif");
    private static final By DOMAINIDFIELD = get("ListDomainsPageBase.DomainIDField");
    private static final By NODOMAINSFIELD = get("ListDomainsPageBase.NoDomainsField");
    private static final String SPECIFIEDDOMAINDELETEBUTTON = "//table[@id='DomainsGrid']//td[text()='%s']/..//a";
    private static final String DOMAIN = "3456";

    public ListDomainsPageBase(WebDriver driver) {
        super(driver);
    }

    public void returnToAllLists(By backToAllListsButton) {
        driver.findElement(backToAllListsButton).click();
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
            return driver.findElement(NODOMAINSFIELD).isDisplayed();
        } catch (NoSuchElementException ex) {
            System.out.println("List still has domains");
            return false;
        }
    }
}
