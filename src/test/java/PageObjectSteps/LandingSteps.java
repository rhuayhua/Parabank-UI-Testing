package PageObjectSteps;

import PageObjects.LoginPage;
import PageObjects.MenuPage;
import Utility.BasePage;
import io.qameta.allure.Step;

public class LandingSteps extends BasePage {

    public LoginPage loginPage;
    public MenuPage menuPage;

    public LandingSteps() {
        loginPage = new LoginPage();
        menuPage = new MenuPage();
    }

    @Step("Verify Footer Links")
    public void verifyFooterLinks() throws InterruptedException {
        loginPage.verifyFooterLinks();
    }

    @Step("Verify Left Menu")
    public void verifyLeftMenuLabels() {
        loginPage.verifyLeftMenu();
    }

    @Step("Verify Login")
    public void verifyLogin(String username, String password, String welcomeMessage) throws InterruptedException {
        loginPage.loginToBank(username,password);
        menuPage.verifySuccessfulLogin(welcomeMessage);
        menuPage.verifyLinkServices();
    }

    @Step("Verify Invalid Password")
    public void verifyInvalidPassword(String username, String password) {
        loginPage.loginToBank(username,password);
        loginPage.verifyErrorMessageLogin();
    }

}
