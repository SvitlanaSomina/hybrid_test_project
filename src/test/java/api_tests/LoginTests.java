package api_tests;

import api_model.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginTests extends BaseApiTest {

    private static final String URL = "https://reqres.in/";
    private static final String EMAIL_SUCCESSFUL_LOGIN = "eve.holt@reqres.in";
    private static final String PASSWORD = "cityslicka";
    private static final String LOGIN_PATH = "api/login";
    private static final int SUCCESS_STATUS_CODE = 200;
    private static final String EXPECTED_TOKEN = "QpwL5tke4Pnpja7X4";
    private static final String PATH_TO_SCHEMA_SUCCESSFUL_LOGIN = "json_schemas/successful_login_schema.json";
    private static final String EMAIL_UNSUCCESSFUL_LOGIN = "peter@klaven";
    private static final int BAD_REQUEST_STATUS_CODE = 400;
    private static final String EXPECTED_ERROR = "Missing password";
    private static final String PATH_TO_SCHEMA_UNSUCCESSFUL_LOGIN = "json_schemas/unsuccessful_login_schema.json";

    @Test
    public void checkStatusCodeSuccessfulLogin(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Login user = new Login(EMAIL_SUCCESSFUL_LOGIN, PASSWORD);
        given()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then()
                .assertThat().statusCode(SUCCESS_STATUS_CODE);
    }

    @Test
    public void checkBodySuccessfulLogin(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Login user = new Login(EMAIL_SUCCESSFUL_LOGIN, PASSWORD);
        SuccessfulLogin successfulLogin = given()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then().log().all()
                .extract().as(SuccessfulLogin.class);
        Assert.assertEquals(successfulLogin.getToken(), EXPECTED_TOKEN);
    }

    @Test
    public void checkJsonSchemaSuccessfulLogin(){
        installSpecification(requestSpec(URL), responseSpecOk200());
        Login user = new Login(EMAIL_SUCCESSFUL_LOGIN, PASSWORD);
        given()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(PATH_TO_SCHEMA_SUCCESSFUL_LOGIN));
    }

    @Test
    public void checkStatusCodeUnsuccessfulLogin(){
        installSpecification(requestSpec(URL), responseSpecError400());
        Login user = new Login(EMAIL_UNSUCCESSFUL_LOGIN, "");
        given()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then()
                .assertThat().statusCode(BAD_REQUEST_STATUS_CODE);
    }

    @Test
    public void checkBodyUnsuccessfulLogin() {
        installSpecification(requestSpec(URL), responseSpecError400());
        Login user = new Login(EMAIL_UNSUCCESSFUL_LOGIN,"");
        UnsuccessfulLogin unsuccessfulLogin = given()
                .body(user)
                .post(LOGIN_PATH)
                .then().log().all()
                .extract().as(UnsuccessfulLogin.class);
        Assert.assertEquals(unsuccessfulLogin.getError(), EXPECTED_ERROR);
    }

    @Test
    public void checkJsonSchemaUnsuccessfulLogin(){
        installSpecification(requestSpec(URL), responseSpecError400());
        Login user = new Login(EMAIL_UNSUCCESSFUL_LOGIN,"");
        given()
                .body(user)
                .when()
                .post(LOGIN_PATH)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(PATH_TO_SCHEMA_UNSUCCESSFUL_LOGIN));
    }
}
