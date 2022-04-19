package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegresinTests {
    @Test
    void successfulLogin() {
        /*
        request: https://reqres.in/api/login
        data:
        {
            "email": "eve.holt@reqres.in",
            "password": "cityslicka"
        }
        response:
        {
            "token": "QpwL5tke4Pnpja7X4"
        }
         */

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void missingPasswordLogin() {
        /*
        request: https://reqres.in/api/login
        data:
        {
            "email": "eve.holt@reqres.in"
        }
        response:
        {
            "error": "Missing password"
        }
         */

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void checkUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("total", is("12"));
    }

    @Test
    void checkStatus200() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    void checkSingleUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                //.body("data.id", is("2"));
                .body("support.url", is("https://reqres.in/#support-heading"));
    }

    @Test
    void createUser() {

        String createData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .body(createData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }

    @Test
    void updateUser() {

        String updateData = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .body(updateData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));

    }

    @Test
    void register() {

        String register = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .body(register)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }
}

