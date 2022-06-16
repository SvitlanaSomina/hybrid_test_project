package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='auth']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='v-login-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='modal-submit-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Невірна адреса електронної пошти (email) або пароль.')]")
    private WebElement warningMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEmailInput() {
        emailInput = ReusableFunctions.getElementByXpath("//input[@id='auth']", driver);
        log.info("Get 'Email' field");
        return emailInput;
    }

    public LoginPage inputEmail(final String email) {
        emailInput.sendKeys(email);
        log.info("Input email into the 'Email' field");
        return this;
    }

    public LoginPage inputPassword(final String password) {
        passwordInput.sendKeys(password);
        log.info("Input password into the 'Password' field");
        return this;
    }

    public void clickOnLoginButton() {
        ReusableFunctions.clickElement(loginButton);
        log.info("Click on the 'Login' button");
    }

    public WebElement getWarningMessage() {
        warningMessage = ReusableFunctions.getElementByXpath("//span[contains(text(),'Невірна адреса електронної пошти (email) або пароль.')]", driver);
        log.info("Get warning message");
        return emailInput;
    }

    public String getWarningMessageText() {
        log.info("Get the text of the warning message");
        return warningMessage.getText();
    }
}
