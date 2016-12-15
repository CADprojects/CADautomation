package page.publishing;

import base.PageBase;
import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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
    private static final By DOMAINSUCCESSFULSETTINGSSAVENOTIF = get("DomainsPage.DomainSuccessfulSettingsSaveNotif");
    private static final String DOMAINPGRATINGVALUE = "20";
    private static final String SPECIFIEDDOMAINNAMECELL = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]";
    private static final String SPECIFIEDDOMAINDELETEBUTTON = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]/..//td[@class='delete_button']/a";
    private static final String SPECIFIEDDOMAINRATINGDROPDOWN = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]/..//select[@class='default_rating']";
    private static final String SPECIFIEDDOMAINBRAINCHECKBOX = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]/..//input[@id='Tag_236']";
    private static final String SPECIFIEDDOMAINSELECTEDRATING = "//table[@class='domainTable myDomains']//td[contains(text(),'%s')]/..//option[@selected='selected']";
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

    public boolean isSpecifiedDomainAdded() {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINNAMECELL, domainName))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteSpecifiedDomain() {
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULCREATIONNOTIF));
        try {
            driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINDELETEBUTTON, domainName))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified domain wasn't created early" + ex.getMessage());
        }
        driver.findElement(CONFIRMDELETIONDOMAINBUTTON).click();
    }

    public boolean isSpecifiedDomainDeleted() {
        WaitersUtils.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(DOMAINSUCCESSFULDELETIONNOTIF));
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULDELETIONNOTIF));
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINNAMECELL, domainName))).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void changeDomainRaiting() {
        try {
            Select ratingDropDown = new Select(driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINRATINGDROPDOWN, domainName))));
            ratingDropDown.selectByValue(DOMAINPGRATINGVALUE);
        } catch (NoSuchElementException ex) {
            System.out.println("Specified domain wasn't created early" + ex.getMessage());
        }
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULSETTINGSSAVENOTIF));
        driver.navigate().refresh();
    }

    public void addBrainExclude() {
        try {
        driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINBRAINCHECKBOX, domainName))).click();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified domain wasn't created early" + ex.getMessage());
        }
        WaitersUtils.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(DOMAINSUCCESSFULSETTINGSSAVENOTIF));
        driver.navigate().refresh();
    }

    public String getDomainDefaultRating() {
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINSELECTEDRATING, domainName))).getText();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified domain wasn't created early" + ex.getMessage());
            return "";
        }
    }

    public boolean isBrainExcludeChecked() {
        try {
            return driver.findElement(By.xpath(String.format(SPECIFIEDDOMAINBRAINCHECKBOX, domainName))).isSelected();
        } catch (NoSuchElementException ex) {
            System.out.println("Specified domain wasn't created early" + ex.getMessage());
            return false;
        }
    }
}
