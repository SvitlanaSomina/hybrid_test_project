package api_tests;

import org.testng.annotations.Test;

import static constants.RequestConstants.EMAIL_SUCCESSFUL_LOGIN;
import static constants.RequestConstants.EMAIL_UNSUCCESSFUL_LOGIN;
import static constants.RequestConstants.LOGIN_PATH;
import static constants.RequestConstants.PASSWORD_SUCCESSFUL_LOGIN;
import static constants.RequestConstants.URL;
import static constants.RequestConstants.WRONG_PASSWORD;
import static constants.ResponseConstants.BAD_REQUEST_STATUS_CODE;
import static constants.ResponseConstants.EXPECTED_ERROR;
import static constants.ResponseConstants.EXPECTED_TOKEN;
import static constants.ResponseConstants.PATH_TO_SCHEMA_SUCCESSFUL_LOGIN;
import static constants.ResponseConstants.PATH_TO_SCHEMA_UNSUCCESSFUL_LOGIN;
import static constants.ResponseConstants.SUCCESS_STATUS_CODE;
import static constants.ResponseConstants.PATH_TO_JSON_FILE_SUCCESSFUL_LOGIN;
import static constants.ResponseConstants.PATH_TO_JSON_FILE_UNSUCCESSFUL_LOGIN;

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
    public void checkBodySuccessfulLoginNoPojo(){
        verifyBodyLoginNoPojo(createLoginUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_LOGIN, PASSWORD_SUCCESSFUL_LOGIN), LOGIN_PATH, PATH_TO_JSON_FILE_SUCCESSFUL_LOGIN);
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
    public void checkBodyUnsuccessfulLoginNoPojo(){
        verifyBodyLoginNoPojo(createLoginUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_LOGIN, WRONG_PASSWORD), LOGIN_PATH, PATH_TO_JSON_FILE_UNSUCCESSFUL_LOGIN);
    }

    @Test
    public void checkJsonSchemaUnsuccessfulLogin(){
        verifyJsonSchemaLogin(createLoginUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_LOGIN, WRONG_PASSWORD), LOGIN_PATH, PATH_TO_SCHEMA_UNSUCCESSFUL_LOGIN);
    }
}
