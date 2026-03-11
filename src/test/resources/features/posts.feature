Feature: Post Management API

  @Regression @Smoke
  Scenario: Successfully create a new post with valid data
    Given I prepare a new post with title "Test Automation" and body "Created with Cucumber" for user 101
    When I send a request to create the post
    Then the post should be created successfully with a valid ID
    And the response should match the original post details
    