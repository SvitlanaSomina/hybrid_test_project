package api_tests;

import org.testng.annotations.Test;

import static constants.RequestConstants.*;
import static constants.ResponseConstants.*;

public class LoginTests extends BaseApiTest {

    @Test
    public void checkStatusCodeSuccessfulLogin(){
        verifyStatusCodeLogin(createLoginUser(URL, responseSpecOk200(),EMAIL_SUCCESSFUL_LOGIN, PASSWORD_SUCCESSFUL_LOGIN), LOGIN_PATH, SUCCESS_STATUS_CODE);
    }

    @Test
    public void checkBodySuccessfulLogin(){
        verifyBodySuccessfulLogin(createLoginUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_LOGIN, PASSWORD_SUCCESSFUL_LOGIN), LOGIN_PATH, EXPECTED_TOKEN);
    }

    @Test
    public void checkJsonSchemaSuccessfulLogin(){
        verifyJsonSchemaLogin(createLoginUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_LOGIN, PASSWORD_SUCCESSFUL_LOGIN), LOGIN_PATH, PATH_TO_SCHEMA_SUCCESSFUL_LOGIN);
    }

    @Test
    public void checkStatusCodeUnsuccessfulLogin(){
        verifyStatusCodeLogin(createLoginUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_LOGIN, WRONG_PASSWORD), LOGIN_PATH, BAD_REQUEST_STATUS_CODE);
    }

    @Test
    public void checkBodyUnsuccessfulLogin(){
        verifyBodyUnsuccessfulLogin(createLoginUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_LOGIN, WRONG_PASSWORD), LOGIN_PATH, EXPECTED_ERROR);
    }

    @Test
    public void checkJsonSchemaUnsuccessfulLogin(){
        verifyJsonSchemaLogin(createLoginUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_LOGIN, WRONG_PASSWORD), LOGIN_PATH, PATH_TO_SCHEMA_UNSUCCESSFUL_LOGIN);
    }
}
