package page.part;

import helper.RandomizersUtils;
import helper.WaitersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 22.12.2016.
 */
public class AddContentNativeOptions {

    private static final By SHORTTITLEINPUT = get("AddContentNativeOptions.ShortTitleInput");
    private static final By SHORTSUMMARYINPUT = get("AddContentNativeOptions.ShortSummaryInput");
    private static final By SUMMARYINPUT = get("AddContentNativeOptions.SummaryInput");
    private static final By CALLTOACTIONINPUT = get("AddContentNativeOptions.CallToActionInput");
    private static final By LOGOINPUT = get("AddContentNativeOptions.LogoInput");
    private static final By LOGO = get("AddContentNativeOptions.Logo");
    private static final int SHORTTITLESTRINGLENGTH = 50;
    private static final int SHORTSUMMARYSTRINGLENGTH = 60;
    private static final int SUMMARYSTRINGLENGTH = 100;
    private static final int CALLTOACTIONSTRINGLENGTH = 20;
    private static final String LOGOPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\Jellyfish.jpg";
    private WebDriver driver;

    public AddContentNativeOptions(WebDriver driver) {
        this.driver = driver;
    }

    public void addAdShortTitle() {
        driver.findElement(SHORTTITLEINPUT).clear();
        driver.findElement(SHORTTITLEINPUT).sendKeys(RandomizersUtils.randomText(SHORTTITLESTRINGLENGTH));
    }

    public void addAdShortSummary() {
        driver.findElement(SHORTSUMMARYINPUT).clear();
        driver.findElement(SHORTSUMMARYINPUT).sendKeys(RandomizersUtils.randomText(SHORTSUMMARYSTRINGLENGTH));
    }

    public void addAdSummary() {
        driver.findElement(SUMMARYINPUT).clear();
        driver.findElement(SUMMARYINPUT).sendKeys(RandomizersUtils.randomText(SUMMARYSTRINGLENGTH));
    }

    public void addAdCallToAction() {
        driver.findElement(CALLTOACTIONINPUT).clear();
        driver.findElement(CALLTOACTIONINPUT).sendKeys(RandomizersUtils.randomText(CALLTOACTIONSTRINGLENGTH));
    }

    public void addAdLogo() {
        driver.findElement(LOGOINPUT).clear();
        driver.findElement(LOGOINPUT).sendKeys(LOGOPATH);
        WaitersUtils.getWaiter(driver).until(ExpectedConditions.visibilityOfElementLocated(LOGO));
    }

    public void addAllNativeOptions() {
        addAdShortTitle();
        addAdShortSummary();
        addAdSummary();
        addAdCallToAction();
        addAdLogo();
    }
}
