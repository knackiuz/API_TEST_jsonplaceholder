package tests;

import config.RequestConfig;
import io.restassured.response.Response;
import models.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.slf4j.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiLombokTest {
    private static final Logger log = LoggerFactory.getLogger(ApiLombokTest.class);

    //Positive test: create and delete user
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
                    .log().ifValidationFails()
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

    @ParameterizedTest(name = "Run {index}: creating user {0}") //Using JUnit internal variables: 'index' - run nimber and '{0}' - argument by index
    @MethodSource("test_data.UserTestData#validUsers")
    //Using parameters - used test data from separate class for the test
    public void createAndDeleteNewUserParameterizedTest(User user){
        User response = given()
                .spec(RequestConfig
                        .getCommonSpec())
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(user.getName()))
                .body("id", notNullValue())
                .extract().as(User.class);

        //System.out.println(response.toString());
        log.info("Created user with ID: {}", response.getId());
        log.debug("Full response: {}", response);


        //delete the user
        given()
                .spec(RequestConfig.getCommonSpec())
                .when()
                .delete("/users/" + response.getId())
                .then()
                .statusCode(204)
                .log().all();

        log.info("Successfully deleted user with ID: {}", response.getId());
    }

    //Negative test: try to create new user with wrong type of property and using Soft Assertion for failures
    @Test
    public void tryCreateAndDeleteUser(){
        SoftAssertions softAssertions = new SoftAssertions();

        User user = User.builder()
                .name("Tom")
                .gender("male")
                .email("")
                .status("active")
                .build();

        Response response =
                given()
                    .spec(RequestConfig.getCommonSpec())
                    .body(user)
                .when()
                        .post("/users")
                .then()
                        .log().all()
                        //remove hard assertion
                        //.statusCode(201)
                        //.body("name", equalTo(user.getName()))
                        //.body("id", notNullValue())
                        .extract().response();

        //Soft assertion
        //softAssertions.assertThat(response.statusCode()).as("Check response status code").isEqualTo(201);

        //softAssertions.assertThat(response.jsonPath().getString("name")).as("Check user name").isEqualTo(user.getName());

        //softAssertions.assertThat(response.jsonPath().getString("id")).as("Check id is not null").isNotNull();

        //And now call all assertions
        softAssertions.assertAll();

    }
}
