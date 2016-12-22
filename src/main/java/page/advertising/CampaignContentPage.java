package page.advertising;

import base.ContentPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.part.AddContentNativeOptions;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 12.12.2016.
 */
public class CampaignContentPage extends ContentPageBase {

    private static final By TITLEINPUT = get("CampaignContentPage.TitleInput");
    private final AddContentNativeOptions ADDCONTENTNAITIVEOPTIONS;

    public CampaignContentPage(WebDriver driver) {
        super(driver);
        ADDCONTENTNAITIVEOPTIONS = new AddContentNativeOptions(driver);
    }

    public void addCampaignAd() {
        startAddingContent();
        addRequiredOptions(TITLEINPUT);
        ADDCONTENTNAITIVEOPTIONS.addAllNativeOptions();
        finishAddingContent();
    }

    public void changeCampaignAdTitle() {
        changeAdTitle(TITLEINPUT);
    }

    public CampaignsPage returnToCampaignsPage() {
        returnToAllSourcesPage();
        return new CampaignsPage(driver);
    }
}
