package models;

import static io.restassured.RestAssured.*;

/**
 * API Client abstraction layer.
 * This class encapsulates the technical implementation of HTTP requests (using Rest Assured),
 * decoupling the API logic from the actual test scenarios.
 */

public class PostClient {
    private final String baseUrl;
    private final String endpoint = "/posts";

    public  PostClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public PostModel createPost(PostModel postModel){
        return given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(postModel)
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)
                .extract().as(PostModel.class);
    }
}
