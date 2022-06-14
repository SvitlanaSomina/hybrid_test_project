package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return emailInput;
    }

    public LoginPage inputEmail(final String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage inputPassword(final String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public WebElement getWarningMessage() {
        return warningMessage;
    }

    public String getWarningMessageText() {
        return warningMessage.getText();
    }
}
