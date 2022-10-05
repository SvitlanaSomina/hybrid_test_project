package api_tests;

import org.testng.annotations.Test;

import static constants.RequestConstants.EMAIL_SUCCESSFUL_REGISTRATION;
import static constants.RequestConstants.EMAIL_UNSUCCESSFUL_REGISTRATION;
import static constants.RequestConstants.PASSWORD_SUCCESSFUL_REGISTRATION;
import static constants.RequestConstants.REGISTER_PATH;
import static constants.RequestConstants.URL;
import static constants.RequestConstants.WRONG_PASSWORD;
import static constants.ResponseConstants.BAD_REQUEST_STATUS_CODE;
import static constants.ResponseConstants.EXPECTED_ERROR;
import static constants.ResponseConstants.EXPECTED_TOKEN;
import static constants.ResponseConstants.PATH_TO_JSON_FILE_SUCCESSFUL_REGISTRATION;
import static constants.ResponseConstants.PATH_TO_JSON_FILE_UNSUCCESSFUL_REGISTRATION;
import static constants.ResponseConstants.PATH_TO_SCHEMA_SUCCESSFUL_REGISTRATION;
import static constants.ResponseConstants.PATH_TO_SCHEMA_UNSUCCESSFUL_REGISTRATION;
import static constants.ResponseConstants.SUCCESS_STATUS_CODE;

public class RegistrationTests extends BaseApiTest {

    @Test
    public void checkStatusCodeSuccessfulRegistration(){
        verifyStatusCodeRegistration(createRegisterUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD_SUCCESSFUL_REGISTRATION), REGISTER_PATH, SUCCESS_STATUS_CODE);
    }

    @Test
    public void checkBodySuccessfulRegistration(){
        verifyBodySuccessfulRegistration(createRegisterUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD_SUCCESSFUL_REGISTRATION), REGISTER_PATH, EXPECTED_TOKEN);
    }

    @Test
    public void checkBodySuccessfulRegistrationNoPojo(){
        verifyBodyRegistrationNoPojo(createRegisterUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD_SUCCESSFUL_REGISTRATION), REGISTER_PATH, PATH_TO_JSON_FILE_SUCCESSFUL_REGISTRATION);
    }

    @Test
    public void checkJsonSchemaSuccessfulRegistration(){
        verifyJsonSchemaRegistration(createRegisterUser(URL, responseSpecOk200(), EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD_SUCCESSFUL_REGISTRATION), REGISTER_PATH, PATH_TO_SCHEMA_SUCCESSFUL_REGISTRATION);
    }

    @Test
    public void checkStatusCodeUnsuccessfulRegistration(){
        verifyStatusCodeRegistration(createRegisterUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_REGISTRATION, WRONG_PASSWORD), REGISTER_PATH, BAD_REQUEST_STATUS_CODE);
    }

    @Test
    public void checkBodyUnsuccessfulRegistration(){
        verifyBodyUnsuccessfulRegistration(createRegisterUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_REGISTRATION, WRONG_PASSWORD), REGISTER_PATH, EXPECTED_ERROR);
    }

    @Test
    public void checkBodyUnsuccessfulRegistrationNoPojo(){
        verifyBodyRegistrationNoPojo(createRegisterUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_REGISTRATION, WRONG_PASSWORD), REGISTER_PATH, PATH_TO_JSON_FILE_UNSUCCESSFUL_REGISTRATION);
    }

    @Test
    public void checkJsonSchemaUnsuccessfulRegistration(){
        verifyJsonSchemaRegistration(createRegisterUser(URL, responseSpecError400(), EMAIL_UNSUCCESSFUL_REGISTRATION, WRONG_PASSWORD), REGISTER_PATH, PATH_TO_SCHEMA_UNSUCCESSFUL_REGISTRATION);
    }
}
