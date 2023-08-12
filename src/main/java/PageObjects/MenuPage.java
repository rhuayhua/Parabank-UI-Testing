package PageObjects;

import Utility.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class MenuPage extends BasePage {

    @FindBy(xpath = "//div[@id='leftPanel']/p[@class='smallText']")
    public WebElement welcomeMessage;

    public MenuPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Verify successful login - Menu Page")
    public void verifySuccessfulLogin(String message) {
        verifyTextInElement(welcomeMessage,message);
    }

    @Step("Verify link services - Menu Page")
    public void verifyLinkServices() throws InterruptedException {
        List<String[]> links = new ArrayList<>();
        links.add(new String[]{"Open New Account", "/parabank/openaccount.htm","Open New Account"});
        links.add(new String[]{"Accounts Overview", "/parabank/overview.htm","Accounts Overview"});
        links.add(new String[]{"Transfer Funds", "/parabank/transfer.htm","Transfer Funds"});
        links.add(new String[]{"Bill Pay", "/parabank/billpay.htm","Bill Payment Service"});

        for (String[] link : links) {
            WebElement element = getDriver().findElement(By.xpath("//div[@id='leftPanel']/ul/li[contains(.,'" + link[0] +  "')]"));
            findVisibleElementWithExplicitWait(element,5);
            clickOnElement(element,5);
            verifyUrlContains(link[1]);
            verifyHeading("//h1", link[2]);
            Thread.sleep(500);
        }
    }
}
