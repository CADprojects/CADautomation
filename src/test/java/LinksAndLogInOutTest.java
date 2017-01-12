import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.ContactPage;
import page.HelpHomePage;
import page.settings.GeneralInfoPage;
import testng.TestListener;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
@Listeners(TestListener.class)
public class LinksAndLogInOutTest extends TestBase {

    private HelpHomePage helpHomePage;
    private ContactPage contactPage;
    private GeneralInfoPage generalInfoPage;

    @Test(groups = {"smoke", "logIn", "links"}, priority = 1)
    public void successfulLogInTest() {
        Assert.assertTrue(widgetReportPage.isLogInSuccessful(), "Log Out link isn't displayed --> log in was unsuccessful");
    }

    @Test(groups = {"smoke", "logIn", "links"}, priority = 1)
    public void logOutTest() {
        widgetReportPage.logOut();
        Assert.assertTrue(logInPage.isLogInButtonDisplayed(), "Log In button isn't displayed --> log out was unsuccessful");
    }

    @Test(groups = {"smoke", "links"}, priority = 6)
    public void helpLinkTest() {
        helpHomePage = widgetReportPage.navigateToHelpPage();
        Assert.assertTrue(helpHomePage.isHelpHomePageOpened(), "Link to Help page is broken");
    }

    @Test(groups = {"smoke", "links"}, priority = 6)
    public void contactLinkTest() {
        contactPage = widgetReportPage.navigateToContactPage();
        Assert.assertTrue(contactPage.isContactPageOpened(), "Link to Contact page is broken");
    }

    @Test(groups = {"smoke", "links"}, priority = 6)
    public void accountSettingsLinkTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPageFromUserPanel();
        Assert.assertTrue(generalInfoPage.isGeneralInfoPageOpened(), "Link to Account seetings is broken");
    }
}
