package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.reqres.RegUser;
import objects.reqres.Resource;
import objects.reqres.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;

public class ReqresTest {

    @Test
    public void checkUserCreation() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();

        Response response = given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_CREATED, "User is NOT created");
    }

    @Test
    public void getTheListOfUsersTest() {

        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK, "There is a problem with receiving list of users");
    }

    @Test
    public void getASingleUserTest() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

    @Test
    public void SingleUserIsNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void checkUserUpdate() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();

        Response response = given()
                .body(user)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_OK, "User is NOT updated");
    }

    @Test
    public void checkUserIsDeleted() {
        Response response = given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_NO_CONTENT, "User is NOT updated");
    }

    @Test
    public void checkUserSuccessfulRegistration() {
        RegUser regUser = RegUser.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(regUser)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void checkUserSuccessfulLogin() {
        RegUser regUser = RegUser.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(regUser)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(HTTP_OK)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void checkSingleResource() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().body().asString();

       Resource resource = new Gson().fromJson(body, Resource.class);
        String color = resource.getData().getName();
        System.out.println(color);

        Assert.assertEquals(color, "fuchsia rose", "Color is not as expected!");

    }
}
