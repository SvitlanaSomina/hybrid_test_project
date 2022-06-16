package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@class='mh-button mh-button--open']")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@class = 'mh-button info']")
    private WebElement personalInfoButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProfileButton() {
        ReusableFunctions.getElementByXpath("//button[@class='mh-button mh-button--open']", driver);
        log.info("Get 'Profile' button");
        return profileButton;
    }

    public void clickOnProfileButtonBeforeLogin() {
        ReusableFunctions.clickElement(profileButton);
        log.info("Click on the 'Profile' button before login");
    }

    public void clickOnProfileButtonAfterLogin() {
        ReusableFunctions.retryFindClick("//button[@class='mh-button mh-button--open']", driver);
        log.info("Click on the 'Profile' button after login");
    }

    public WebElement getPersonalInfoButton() {
        ReusableFunctions.getElementByXpath("//a[@class = 'mh-button info']", driver);
        log.info("Get 'Personal Info' button");
        return personalInfoButton;
    }

    public void clickOnPersonalInfoButton() {
        ReusableFunctions.clickElement(personalInfoButton);
        log.info("Click on the 'Personal Info' button");
    }
}
