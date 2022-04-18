package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class SelenoidTests {
    //make request to https://selenoid.autotests.cloud/status
    //total 20

    @Test
    void checkTotal() {
        given()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("Total", is(20));
    }

}
