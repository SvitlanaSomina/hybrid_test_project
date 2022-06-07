package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@class='mh-button mh-button--open']")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@class = 'mh-button info']")
    private WebElement personalInfoButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProfileButton() {
        return profileButton;
    }

    public WebElement getPersonalInfoButton() {
        return personalInfoButton;
    }

    public void clickOnProfileButton() {
        profileButton.click();
    }

    public void clickOnPersonalInfoButton() {
        personalInfoButton.click();
    }
}
