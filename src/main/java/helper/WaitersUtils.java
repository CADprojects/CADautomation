package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class WaitersUtils {

    public static WebDriverWait getWaiter(WebDriver driver, long timeOut) {
        return new WebDriverWait(driver, timeOut);
    }
}
