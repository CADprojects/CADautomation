package page.publishing;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 13.12.2016.
 */
public class InstallationCodePage extends PageBase{

    private static final By WIDGETCODETEXTAREA = get("InstallationCodePage.WidgetCodeTextArea");
    private static final By BACKTOWIDGETSBUTTON = get("InstallationCodePage.BackToWidgetsButton");

    public InstallationCodePage(WebDriver driver) {
        super(driver);
    }

    public String receiveWidgetId() {
        String widgetCodeText = driver.findElement(WIDGETCODETEXTAREA).getText();
        String widgetID = "";
        String[] subStrings = widgetCodeText.split("\n");
        for (String row: subStrings) {
            if (row.contains("wid:")) {
                widgetID = row.substring(row.indexOf("\"")+1, row.lastIndexOf("\""));
            }
        }
        return widgetID;
    }

    public WidgetsPage returnToWidgetsPage() {
        String widgetID = receiveWidgetId();
        driver.findElement(BACKTOWIDGETSBUTTON).click();
        return new WidgetsPage(driver, widgetID);
    }
}
