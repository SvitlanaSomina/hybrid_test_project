package api_tests;

import api_model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseApiTest {

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

    public static void verifyBodyLoginNoPojo(Login user, String loginPath, String filePath) {
        String responseBody =  given()
                .body(user)
                .when()
                .post(loginPath)
                .then().log().all()
                .extract().body().asString();
        Map<String, String> mapFromResponse = getMapFromResponseBody(responseBody);
        Map<String, String> mapFromFile;
        mapFromFile = readJsonFromFile(new File(filePath));
        Assert.assertEquals(mapFromResponse, mapFromFile);
    }

    public static void verifyBodyRegistrationNoPojo(Register user, String registrationPath, String filePath) {
        String responseBody =  given()
                .body(user)
                .when()
                .post(registrationPath)
                .then().log().all()
                .extract().body().asString();
        Map<String, String> mapFromResponse = getMapFromResponseBody(responseBody);
        Map<String, String> mapFromFile;
        mapFromFile = readJsonFromFile(new File(filePath));
        Assert.assertEquals(mapFromResponse, mapFromFile);
    }

    private static Map<String, String> getMapFromResponseBody(String responseBody) {
        Map<String, String> mapFromResponseBody = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapFromResponseBody = mapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return mapFromResponseBody;
    }

    public static Map<String, String> readJsonFromFile(File inFile){
        Map<String, String> mapFromFile = null;
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = new byte[0];
        try {
            json = Files.readAllBytes(inFile.toPath());
            mapFromFile = mapper.readValue(json, new TypeReference<>() {});
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return mapFromFile;
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
