package api_tests;

import api_model.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseApiTest {
    private  static Logger logger = LogManager.getLogger(BaseApiTest.class);

    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecError400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static Login createLoginUser(String url, ResponseSpecification responseSpecification, String email, String password) {
        installSpecification(requestSpec(url), responseSpecification);
        return new Login(email, password);
    }

    public static Register createRegisterUser(String url, ResponseSpecification responseSpecification, String email, String password) {
        installSpecification(requestSpec(url), responseSpecification);
        return new Register(email, password);
    }

    public static void verifyStatusCodeLogin(Login user, String path, int statusCode) {
        given()
                .filter((request, response, ctx) -> {
                    Response resp = ctx.next(request, response);
                    if (resp.statusCode() >= 400) {
                        logger.log(Level.ERROR, request.getMethod() + " " + request.getURI() + " => "
                                + response.getStatusCode() + " " + response.getStatusLine());
                    } else {
                        logger.log(Level.INFO, request.getMethod() + " " + request.getURI() + " => "
                                + response.getStatusCode() + " " + response.getStatusLine());
                    }
                    return resp;
                })
                .body(user)
                .when()
                .post(path)
                .then()
                .assertThat().statusCode(statusCode);
    }

    public static void verifyStatusCodeRegistration(Register user, String path, int statusCode) {
        given()
                .body(user)
                .when()
                .post(path)
                .then()
                .log().status()
                .assertThat().statusCode(statusCode);
    }

    public static void verifyBodySuccessfulLogin(Login user, String path, String token) {
        SuccessfulLogin successfulLogin =  given()
                .body(user)
                .when()
                .post(path)
                .then().log().all()
                .extract().as(SuccessfulLogin.class);
        Assert.assertEquals(successfulLogin.getToken(), token);
    }

    public static void verifyBodySuccessfulRegistration(Register user, String path, String token) {
        SuccessfulRegister successfulRegister =  given()
                .body(user)
                .when()
                .post(path)
                .then().log().all()
                .extract().as(SuccessfulRegister.class);
        Assert.assertEquals(successfulRegister.getToken(), token);
    }

    public static void verifyBodyUnsuccessfulLogin(Login user, String path, String error) {
        UnsuccessfulLogin unSuccessfulLogin =  given()
                .body(user)
                .when()
                .post(path)
                .then().log().all()
                .extract().as(UnsuccessfulLogin.class);
        Assert.assertEquals(unSuccessfulLogin.getError(), error);
    }

    public static void verifyBodyUnsuccessfulRegistration(Register user, String path, String error) {
        UnsuccessfulRegister unsuccessfulRegister =  given()
                .body(user)
                .when()
                .post(path)
                .then().log().all()
                .extract().as(UnsuccessfulRegister.class);
        Assert.assertEquals(unsuccessfulRegister.getError(), error);
    }

    public static void verifyJsonSchemaLogin(Login user, String path, String pathToSchema) {
        given()
                .body(user)
                .when()
                .post(path)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(pathToSchema));
    }

    public static void verifyJsonSchemaRegistration(Register user, String path, String pathToSchema) {
        given()
                .body(user)
                .when()
                .post(path)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath(pathToSchema));
    }
}
