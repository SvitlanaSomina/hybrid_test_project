package ui_tests;

import dataProvider.ConfigFileReader;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class LoginTests extends BaseUiTest {
    private static final String EMAIL = ConfigFileReader.getEmail();
    private static final String PASSWORD = ConfigFileReader.getPassword();
    private static final String INVALID_PASSWORD = "g2dkit";
    private static final String WARNING_MESSAGE_TEXT = "Невірна адреса електронної пошти (email) або пароль.";

    @Test
    public void checkLoginWithValidEmailAndPassword() {
        getHomePage()
                .clickOnProfileButtonBeforeLogin()
                .waitVisibilityOfElement(getLoginPage().getEmailInput());
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(PASSWORD)
                .clickOnLoginButton();
        getHomePage().waitVisibilityOfElement(getHomePage().getProfileButton());
        getHomePage()
                .clickOnProfileButtonAfterLogin()
                .waitVisibilityOfElement(getHomePage().getPersonalInfoButton());
        getHomePage().clickOnPersonalInfoButton();
        assertEquals(getPersonalInfoPage().getEmail(), EMAIL);
    }

    @Test
    public void checkLoginWithValidEmailAndInvalidPassword() {
        getHomePage()
                .clickOnProfileButtonBeforeLogin()
                .waitVisibilityOfElement(getLoginPage().getEmailInput());
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickOnLoginButton();
        getLoginPage().waitVisibilityOfElement(getLoginPage().getWarningMessage());
        assertEquals(getLoginPage().getWarningMessageText(), WARNING_MESSAGE_TEXT);
    }
}
