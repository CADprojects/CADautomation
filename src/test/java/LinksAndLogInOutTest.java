import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andrei.Ostrovski on 15.11.2016.
 */
public class LinksAndLogInOutTest extends TestBase {

    @Test
    public void successfulLogInTest() {
        Assert.assertTrue(widgetReportPage.isLogInSuccessful(), "Log Out link isn't displayed --> log in was unsuccessful");
    }

    @Test
    public void logOutTest() {
        widgetReportPage.logOut();
        Assert.assertTrue(logInPage.isLogInButtonDisplayed(), "Log In button isn't displayed --> log out was unsuccessful");

    }

}
