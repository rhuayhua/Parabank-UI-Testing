package PageObjects;

import Utility.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//div/input[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//form[@name='login']/*/b[contains(.,'Username')]")
    public WebElement usernameLabel;

    @FindBy(xpath = "//form[@name='login']/*/b[contains(.,'Password')]")
    public WebElement passwordLabel;

    @FindBy(xpath = "//div/form/div/input[@name='username']")
    public WebElement usernameInput;

    @FindBy(xpath = "//div/form/div/input[@name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//div[@id='rightPanel']/p")
    public WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Verify footer links - Login Page")
    public void verifyFooterLinks() throws InterruptedException {
        List<String[]> links = new ArrayList<>();
        links.add(new String[]{"Home", "/parabank/index.htm"});
        links.add(new String[]{"About Us", "/parabank/about.htm"});
        links.add(new String[]{"Site Map", "/parabank/sitemap.htm"});
        links.add(new String[]{"Contact Us", "/parabank/contact.htm"});

        for (String[] link : links) {
            WebElement element = getDriver().findElement(By.xpath("//div[@id='footerPanel']/*/li[contains(.,'" + link[0] + "')]"));
            findVisibleElementWithExplicitWait(element,5);
            clickOnElement(element,5);
            verifyUrlContains(link[1]);
            Thread.sleep(500);
        }
    }

    @Step("Verify Left Menu - Login Page")
    public void verifyLeftMenu() {
        List<String> labels = new ArrayList<>();
        labels.add("Solutions");
        labels.add("About Us");
        labels.add("Services");
        labels.add("Products");
        labels.add("Locations");
        labels.add("Admin Page");

        for (String label : labels) {
            WebElement element = getDriver().findElement(By.xpath("//ul[@class='leftmenu']/li[contains(.,'" + label + "')]"));
            findVisibleElementWithExplicitWait(element,5);
        }
    }

    @Step("Login to Bank")
    public void loginToBank(String username, String password) {
        typeInElement(usernameInput,username,5);
        typeInElement(passwordInput,password,5);
        clickOnElement(loginBtn,5);
    }

    @Step("Verify Error Message Invalid Password - Login Page")
    public void verifyErrorMessageLogin() {
        verifyHeading("//h1","Error!");
        verifyTextInElement(errorMessage,"The username and password could not be verified.");
    }

}
