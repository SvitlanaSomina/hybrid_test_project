package ui_tests;

import dataProvider.ConfigFileReader;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTests extends BaseUiTest {

    ConfigFileReader configFileReader = new ConfigFileReader();
    private String EMAIL = configFileReader.getEmail();
    private String PASSWORD = configFileReader.getPassword();
    private String INVALID_PASSWORD = "g2dkit";
    private String WARNING_MESSAGE_TEXT = "Невірна адреса електронної пошти (email) або пароль.";


    @Test
    public void checkLoginWithValidEmailAndPassword() throws InterruptedException {
        getHomePage().clickOnProfileButton();
        getLoginPage().waitVisibilityOfElement(getLoginPage().getEmailInput());
        getLoginPage().inputEmail(EMAIL);
        getLoginPage().inputPassword(PASSWORD);
        getLoginPage().clickOnLoginButton();
        getHomePage().waitVisibilityOfElement(getHomePage().getProfileButton());
        Thread.sleep(10000);
        getHomePage().clickOnProfileButton();
        getHomePage().waitVisibilityOfElement(getHomePage().getPersonalInfoButton());
        getHomePage().clickOnPersonalInfoButton();
        assertEquals(getPersonalInfoPage().getEmail(), EMAIL);
    }

    @Test
    public void checkLoginWithValidEmailAndInvalidPassword() {
        getHomePage().clickOnProfileButton();
        getLoginPage().waitVisibilityOfElement(getLoginPage().getEmailInput());
        getLoginPage().inputEmail(EMAIL);
        getLoginPage().inputPassword(INVALID_PASSWORD);
        getLoginPage().clickOnLoginButton();
        getLoginPage().waitVisibilityOfElement(getLoginPage().getWarningMessage());
        assertEquals(getLoginPage().getWarningMessageText(), WARNING_MESSAGE_TEXT);
    }
}
