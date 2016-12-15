package page.advertising;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 12.12.2016.
 */
public class AdsPage extends PageBase {

    private static final By CAMPAIGNIDFIELD = get("AdsPage.CampaignIDField");
    private static final By MYCAMPAIGNSLINK = get("AdsPage.MyCampaignsLinks");
    private static final By ADDCONTENTBUTTON = get("AdsPage.AddContentButton");

    public AdsPage(WebDriver driver) {
        super(driver);
    }

    public CampaignsPage returnToCampaignsPage() {
        Pattern pattern = Pattern.compile("[0-9]+");
        String campaignId = null;
        Matcher matcher = pattern.matcher(driver.findElement(CAMPAIGNIDFIELD).getText());
        if (matcher.find()) {
            campaignId = matcher.group(0);
        }
        driver.findElement(MYCAMPAIGNSLINK).click();
        return new CampaignsPage(driver, campaignId);
    }
}
