package com.piotrschreiner.automation.config;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

/**
 * Base class for all API and E2E tests.
 * Sets up the base URI for Rest Assured to point to the running Spring Boot
 * service.
 */
public class TestBase {

    // The Spring Boot application runs on this port
    private static final int APP_PORT = 8080;

    @BeforeAll
    public static void setup() {
        // Configure Rest Assured to point to the local Spring Boot service
        // This is necessary because the application is running in a separate process
        // (your other terminal)
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = APP_PORT;
    }
}