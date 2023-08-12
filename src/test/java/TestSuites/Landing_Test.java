package TestSuites;

import PageObjectSteps.BaseTest;
import io.qameta.allure.Epic;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.IOException;

public class Landing_Test extends BaseTest {

    @Epic("Landing Test")

    @Test(description = "LT01_Landing_Verify_LeftMenuLabels", alwaysRun = true, priority = 0, groups = {"Landing"})
    public void LT01_Landing_Verify_LeftMenuLabels() {
        landingSteps.verifyLeftMenuLabels();
    }

    @Test(description = "LT02_Landing_Verify_FooterLinks", alwaysRun = true, priority = 1, groups = {"Landing"})
    public void LT02_Landing_Verify_FooterLinks() throws InterruptedException {
        landingSteps.verifyFooterLinks();
    }

    @Test(description = "LT03_Landing_Verify_ErrorMessage_IncorrectPassword", alwaysRun = true, priority = 2, groups = {"Landing"})
    public void LT03_Landing_Verify_ErrorMessage_IncorrectPassword() throws InterruptedException, IOException, ParseException {
        String user = getTCData(getMyMap("TCName"), "username","landing");
        String pwd = getTCData(getMyMap("TCName"), "password","landing");
        landingSteps.verifyInvalidPassword(user,pwd);
    }
    @Test(description = "LT04_Landing_Verify_SuccessfulLogin", alwaysRun = true, priority = 3, groups = {"Landing"})
    public void LT04_Landing_Verify_SuccessfulLogin() throws InterruptedException, IOException, ParseException {
        String user = getTCData(getMyMap("TCName"), "username","landing");
        String pwd = getTCData(getMyMap("TCName"), "password","landing");
        landingSteps.verifyLogin(user,pwd, "Welcome johh smith");
    }
}
