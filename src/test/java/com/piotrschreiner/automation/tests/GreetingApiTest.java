package com.piotrschreiner.automation.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.piotrschreiner.automation.config.TestBase;

import io.restassured.http.ContentType;

@DisplayName("Greeting API Test Suite")
@Tag("API")
public class GreetingApiTest extends TestBase {

    @Test
    @DisplayName("API-GET-001: Should return status 200 and default content")
    void testGetDefaultGreeting() {
        given()
                .when()
                .get("/greeting")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("content", equalTo("Hello, World!"));
    }
}