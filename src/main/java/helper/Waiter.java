package helper;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class Waiter {

    public static WebDriverWait getWaiter() {
        return new WebDriverWait(PageBase.getDriver(), 30);
    }
}
