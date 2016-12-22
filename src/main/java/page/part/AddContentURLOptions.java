package page.part;

import helper.RandomizersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 16.12.2016.
 */
public class AddContentURLOptions {

    private static final By NEEDSCANCHECKBOX = get("AddContentURLOptions.NeedScanCheckbox");
    private static final By CONTINUEADDBUTTON = get("AddContentURLOptions.ContinueAddContentButton");
    private static final By ADURLINPUT = get("AddContentURLOptions.AdURLInput");
    private static final By CANCELADDADBUTTON = get("AddContentURLOptions.CancelAddContentButton");
    private static final String ADURL = "http://testurl%s.net";
    private WebDriver driver;

    public AddContentURLOptions(WebDriver driver) {
        this.driver = driver;
    }

    public void addAdURLWithoutScan() {
        driver.findElement(ADURLINPUT).sendKeys(String.format(ADURL, RandomizersUtils.randomPrefix()));
        driver.findElement(NEEDSCANCHECKBOX).click(); // uncheck scan for an image and headline
        driver.findElement(CONTINUEADDBUTTON).click();
    }

    public void cancelAdAdd() {
        driver.findElement(CANCELADDADBUTTON).click();
    }
}
