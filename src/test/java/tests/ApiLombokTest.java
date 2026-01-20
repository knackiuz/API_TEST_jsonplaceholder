package tests;

import cofig.RequestConfig;
import models.User;
import org.junit.jupiter.api.Test;
import org.slf4j.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiLombokTest {
    private static final Logger log = LoggerFactory.getLogger(ApiLombokTest.class);

    //Test: create and delete user
    @Test
    public void createAndDeleteUser(){
        User user = User.builder()
                .name("Alex")
                .gender("male")
                .email("alex" + System.currentTimeMillis() + "@mail.com")
                .status("active")
                .build();

        User response = given()
                .spec(RequestConfig.getCommonSpec())
                    .body(user)
                .when()
                    .post("/users")
                .then()
                    .statusCode(201)
                    .body("name", equalTo(user.getName()))
                    .body("id", notNullValue())
                    .extract().as(User.class);
        log.info("Response from POST request: {}", response.toString());

                //delete the user
                given()
                    .spec(RequestConfig.getCommonSpec())
                .when()
                    .delete("/users/" + response.getId())
                .then()
                        .statusCode(204)
                        .log().all();
    }
}
