package tests;

import models.PostModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//API Test class demonstrating best practices for Rest Assured:
//- Uses the given/when/then structure.
//- Uses POJO (PostModel) for request/response data handling.
public class ApiTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS_ENDPOINT = "/posts";

    //Test case to verify successful creation of a new post resource using POJO.
    //This method shows both Hamcrest validation and Java/JUnit assertion methods.
    @Test
    void shouldCreateNewPostUsingPojoSuccessfully() {
        //Arrange (Data Preparation): Create the POJO object (PostModel) which will be sent as the request body.
        PostModel requestPayLoad = new PostModel(
                "Test Automation Assigment",
                "Testing API resource creation with Rest Assured and POJO.",
                101
        );
        //Act & Assert (Execution and Validation)
        PostModel responseBody = given()                            // GIVEN (Configuration Step): Set the base parameters for the request
                .baseUri(BASE_URL)
                .contentType("application/json")                 // Specify that the request body is in JSON format
                .body(requestPayLoad)                               // Rest Assured automatically serializes the requestPayload object to JSON
                .when()                                             // WHEN (Action Step): Execute the POST request to the specified endpoint
                .post(POSTS_ENDPOINT)
                .then()                                             // THEN (Validation Step): Assertions on the server response
                .statusCode(201).body("id", notNullValue())    // Data Validation (Hamcrest Matchers): Ensure the response contains a generated 'id'
                .extract().as(PostModel.class);                     // Extraction: Deserialize the JSON response body back into a PostModel object

        //Secondary Java Validation (Using JUnit Assertions):
        //These checks are explicit and preferred for validating complex business logic.
        //Assert that the server successfully generated an ID
        assertNotNull(responseBody.getId(), "Generated ID should not be null in the response.");

        //Assert that the title we sent matches the title returned in the response
        assertEquals(requestPayLoad.getTitle(), responseBody.getTitle(), "Returned title must match the sent title.");

        //Assert that the userId was also correctly echoed back
        assertEquals(requestPayLoad.getUserId(), responseBody.getUserId(), "Returned userId must match the sent userId");
    }
}
