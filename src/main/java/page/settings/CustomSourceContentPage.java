package page.settings;

import base.ContentPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 21.12.2016.
 */
public class CustomSourceContentPage extends ContentPageBase {

    private static final By TITLEINPUT = get("CustomSourceContentPage.TitleInput");

    public CustomSourceContentPage(WebDriver driver) {
        super(driver);
    }

    public void addCustomSourceAd() {
        startAddingContent();
        addRequiredOptions(TITLEINPUT);
        finishAddingContent();
    }

    public void changeCustomSourceAdTitle() {
        changeAdTitle(TITLEINPUT);
    }

    public CustomSourcesPage returnToCustomSourcesPage() {
        returnToAllSourcesPage();
        return new CustomSourcesPage(driver);
    }
}
