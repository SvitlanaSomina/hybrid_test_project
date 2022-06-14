package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

    public HomePage clickOnProfileButtonAfterLogin() {
        try {
            profileButton.click();
        } catch(StaleElementReferenceException staleElementReferenceException) {
            profileButton = driver.findElement(By.xpath("//button[@class='mh-button mh-button--open']"));
            profileButton.click();
        }
        return this;
    }

    public WebElement getPersonalInfoButton() {
        return personalInfoButton;
    }

    public LoginPage clickOnProfileButtonBeforeLogin() {
        profileButton.click();
        return new LoginPage(driver);
    }

    public void clickOnPersonalInfoButton() {
        personalInfoButton.click();
    }
}
