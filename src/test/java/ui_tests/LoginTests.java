package ui_tests;

import utils.ConfigFileReader;
import org.testng.annotations.Test;
import utils.ReusableFunctions;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTests extends BaseUiTest {
    private static final String EMAIL = ConfigFileReader.getEmail();
    private static final String PASSWORD = ConfigFileReader.getPassword();
    private static final String INVALID_PASSWORD = "g2dkit";
    private static final String WARNING_MESSAGE_TEXT = "Невірна адреса електронної пошти (email) або пароль.";

    @Test
    public void checkLoginWithValidEmailAndPassword() {
        getHomePage().clickProfileButtonBeforeLogin();
        ReusableFunctions.implicitWait(driver);
        getLoginPage().getEmailInput();
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(PASSWORD)
                .clickLoginButton();
        getHomePage().getProfileButton();
        ReusableFunctions.implicitWait(driver);
        getHomePage().clickProfileButtonAfterLogin();
        getHomePage().getPersonalInfoButton();
        getHomePage().clickPersonalInfoButton();
        assertEquals(getPersonalInfoPage().getEmail(), EMAIL);
    }

    @Test
    public void checkLoginWithValidEmailAndInvalidPassword() {
        getHomePage().clickProfileButtonBeforeLogin();
        ReusableFunctions.implicitWait(driver);
        getLoginPage().getEmailInput();
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickLoginButton();
        getLoginPage().getWarningMessage();
        assertEquals(getLoginPage().getWarningMessageText(), WARNING_MESSAGE_TEXT);
    }
}
