package tests;

import base.BaseTest;
import models.User;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class WiremockTest extends BaseTest {

    //Positive test: create new user with mock
    @Test
    public void createNewUserWithMock(){
        User user = User.builder()
                .name("Sarah")
                .gender("female")
                .email("sarah" + System.currentTimeMillis() + "@mail.com")
                .status("active")
                .build();

        stubFor(post(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "appication/json")
                        .withBody("{\"id\": 999, \"name\": \"NewUserMock\", \"status\":\"active\" }")
                ));
        given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(user)
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("id", equalTo(999))
                .log().all();
    }

    //Negative test: try to create new user with invalid parameters (email)
    @Test
    public void tryToCreateNewUserWithWrongEmail(){
        User user = User.builder()
                .name("John")
                .gender("male")
                .email("")
                .status("active")
                .build();

        stubFor(post(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Invalid email address\"}")
                )
        );

        given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(user)
        .when()
                .post("/users")
        .then()
                .statusCode(400)
                .body("message", equalTo("Invalid email address"))
                .log().all();
    }
}
