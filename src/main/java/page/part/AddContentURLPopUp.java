package page.part;

import helper.RandomizersUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 16.12.2016.
 */
public class AddContentURLPopUp {

    private static final By NEEDSCANCHECKBOX = get("AddContentURLPopUp.NeedScanCheckbox");
    private static final By CONTINUEADDBUTTON = get("AddContentURLPopUp.ContinueAddContentButton");
    private static final By ADURLINPUT = get("AddContentURLPopUp.AdURLInput");
    private static final By CANCELADDADBUTTON = get("AddContentURLPopUp.CancelAddContentButton");
    private static final String ADURL = "http://testurl%s.net";
    private WebDriver driver;

    public AddContentURLPopUp(WebDriver driver) {
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
