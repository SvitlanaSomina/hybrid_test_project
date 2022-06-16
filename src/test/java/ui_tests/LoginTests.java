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
        getHomePage().clickOnProfileButtonBeforeLogin();
        ReusableFunctions.implicitWait(driver);
        getLoginPage().getEmailInput();
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(PASSWORD)
                .clickOnLoginButton();
        getHomePage().getProfileButton();
        ReusableFunctions.implicitWait(driver);
        getHomePage().clickOnProfileButtonAfterLogin();
        getHomePage().getPersonalInfoButton();
        getHomePage().clickOnPersonalInfoButton();
        assertEquals(getPersonalInfoPage().getEmail(), EMAIL);
        log.info("Check that email is displayed on the Personal Info page");
    }

    @Test
    public void checkLoginWithValidEmailAndInvalidPassword() {
        getHomePage().clickOnProfileButtonBeforeLogin();
        ReusableFunctions.implicitWait(driver);
        getLoginPage().getEmailInput();
        getLoginPage()
                .inputEmail(EMAIL)
                .inputPassword(INVALID_PASSWORD)
                .clickOnLoginButton();
        getLoginPage().getWarningMessage();
        assertEquals(getLoginPage().getWarningMessageText(), WARNING_MESSAGE_TEXT);
        log.info("Check that the text of the warning message is: " + WARNING_MESSAGE_TEXT);
    }
}
