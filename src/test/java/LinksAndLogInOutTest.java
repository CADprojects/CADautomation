import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ContactPage;
import page.HelpHomePage;
import page.settings.GeneralInfoPage;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class LinksAndLogInOutTest extends TestBase {

    private HelpHomePage helpHomePage;
    private ContactPage contactPage;
    private GeneralInfoPage generalInfoPage;

    @Test
    public void successfulLogInTest() {
        Assert.assertTrue(widgetReportPage.isLogInSuccessful(), "Log Out link isn't displayed --> log in was unsuccessful");
    }

    @Test
    public void logOutTest() {
        widgetReportPage.logOut();
        Assert.assertTrue(logInPage.isLogInButtonDisplayed(), "Log In button isn't displayed --> log out was unsuccessful");
    }

    @Test
    public void helpLinkTest() {
        helpHomePage = widgetReportPage.navigateToHelpPage();
        Assert.assertTrue(helpHomePage.isHelpHomePageOpened(), "Link to Help page is broken");
    }

    @Test
    public void contactLinkTest() {
        contactPage = widgetReportPage.navigateToContactPage();
        Assert.assertTrue(contactPage.isContactPageOpened(), "Link to Contact page is broken");
    }

    @Test
    public void accountSettingsLinkTest() {
        generalInfoPage = widgetReportPage.navigateToGeneralInfoPageFromUserPanel();
        Assert.assertTrue(generalInfoPage.isGeneralInfoPageOpened(), "Link to Account seetings is broken");
    }
}
