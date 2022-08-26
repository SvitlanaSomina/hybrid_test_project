package api_tests;

import api_model.Register;
import api_model.SuccessfulRegister;
import api_model.UnsuccessfulRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RegistrationTests extends BaseApiTest {

    private static final String URL = "https://reqres.in/";
    private static final String EMAIL_SUCCESSFUL_REGISTRATION = "eve.holt@reqres.in";
    private static final String PASSWORD = "pistol";
    private static final String REGISTER_PATH = "api/register";
    private static final int SUCCESS_STATUS_CODE = 200;
    private static final int EXPECTED_ID = 4;
    private static final String EXPECTED_TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String PATH_TO_SCHEMA_SUCCESSFUL_REGISTRATION = "json_schemas/successful_registration_schema.json";
    private static final String EMAIL_UNSUCCESSFUL_REGISTRATION = "sydney@fife";
    private static final int BAD_REQUEST_STATUS_CODE = 400;
    private static final String EXPECTED_ERROR = "Missing password";
    private static final String PATH_TO_SCHEMA_UNSUCCESSFUL_REGISTRATION = "json_schemas/unsuccessful_registration_schema.json";

    @Test
    public void checkStatusCodeSuccessfulRegistration(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Register user = new Register(EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD);
        given()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .assertThat().statusCode(SUCCESS_STATUS_CODE);
    }

    @Test
    public void checkBodySuccessfulRegistration(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Register user = new Register(EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD);
        SuccessfulRegister successfulRegister = given()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then().log().all()
                .extract().as(SuccessfulRegister.class);
        Assert.assertEquals(successfulRegister.getId(), EXPECTED_ID);
        Assert.assertEquals(successfulRegister.getToken(), EXPECTED_TOKEN);
    }

    @Test
    public void checkJsonSchemaSuccessfulRegistration(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Register user = new Register(EMAIL_SUCCESSFUL_REGISTRATION, PASSWORD);
        given()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(PATH_TO_SCHEMA_SUCCESSFUL_REGISTRATION));
    }

    @Test
    public void checkStatusCodeUnsuccessfulRegistration(){
        installSpecification(requestSpec(URL), responseSpecError400());
        Register user = new Register(EMAIL_UNSUCCESSFUL_REGISTRATION, "");
        given()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .assertThat().statusCode(BAD_REQUEST_STATUS_CODE);
    }

    @Test
    public void checkBodyUnsuccessfulRegistration() {
        installSpecification(requestSpec(URL), responseSpecError400());
        Register user = new Register(EMAIL_UNSUCCESSFUL_REGISTRATION, "");
        UnsuccessfulRegister unsuccessfulRegister = given()
                .body(user)
                .post(REGISTER_PATH)
                .then().log().all()
                .extract().as(UnsuccessfulRegister.class);
        Assert.assertEquals(unsuccessfulRegister.getError(), EXPECTED_ERROR);
    }

    @Test
    public void checkJsonSchemaUnsuccessfulRegistration(){
        installSpecification(requestSpec(URL), responseSpecError400());
        Register user = new Register(EMAIL_UNSUCCESSFUL_REGISTRATION, "");
        given()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(PATH_TO_SCHEMA_UNSUCCESSFUL_REGISTRATION));
    }
}
