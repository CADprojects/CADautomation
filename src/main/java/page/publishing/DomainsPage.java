package page.publishing;

import base.PageBase;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class DomainsPage extends PageBase {

    private static final By ADDNEWDOMAININPUT = get("DomainsPage.AddNewDomainInput");
    private static final By ADDNEWDOMAINBUTTON = get("DomainsPage.AddNewDomainButton");
    private static final By DOMAINSUCCESSFULCREATIONNOTIF = get("DomainsPage.DomainSuccessfulCreationNotif");
    private static final By CONFIRMDELETIONDOMAINBUTTON = get("DomainsPage.ConfirmDeletionDomainButton");
    private static final By DOMAINSUCCESSFULDELETIONNOTIF = get("DomainsPage.DomainSuccessfulDeletionNotif");
    private String specifiedDomainNameCell = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]";
    private String specifiedDomainDeleteButton = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]/..//td[@class='delete_button']/a";
    private String domainName;

    public DomainsPage(WebDriver driver) {
        super(driver);
    }

    public String getDomainName() {
        return domainName;
    }

    public void addNewDomain() {
        domainName = String.format("testdomain%s.com", RandomizersUtils.random_prefix());
        driver.findElement(ADDNEWDOMAININPUT).sendKeys(domainName);
        driver.findElement(ADDNEWDOMAINBUTTON).click();
    }

    public boolean isNewDomainCreated(String domainName) {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(specifiedDomainNameCell, domainName))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedDomain(String domainName) {
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        driver.findElement(By.xpath(String.format(specifiedDomainDeleteButton, domainName))).click();
        driver.findElement(CONFIRMDELETIONDOMAINBUTTON).click();
    }

    public boolean isSpecifiedDomainDeleted(String domainName) {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(DOMAINSUCCESSFULDELETIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULDELETIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(specifiedDomainNameCell, domainName))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
