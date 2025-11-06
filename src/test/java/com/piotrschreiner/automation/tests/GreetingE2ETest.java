package com.piotrschreiner.automation.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.piotrschreiner.automation.actions.GreetingActions;
import com.piotrschreiner.automation.config.PlaywrightTestBase;

@DisplayName("E2E Greeting Content Validation")
@Tag("E2E")
public class GreetingE2ETest extends PlaywrightTestBase {

    private static final long MAX_LOAD_TIME_MS = 250;

    @Test
    @DisplayName("E2E-GREET-001: Should check if the default greeting content is present")
    void checkDefaultGreetingContent() {

        GreetingActions greetingActions = new GreetingActions(page);

        String actualGreetingText = greetingActions.getGreetingText();

        assertTrue(actualGreetingText.contains("Hello, World!"),
                "Der abgerufene Text sollte 'Hello, World!' enthalten, war aber: " + actualGreetingText);
    }

    @Test
    @DisplayName("E2E-GREET-002: Should check if the greeting content reflects the query parameter")
    void checkGreetingContentWithParameter() {

        GreetingActions greetingActions = new GreetingActions(page);
        String testName = "Automation";

        greetingActions.navigateToGreetingWithName(testName);

        String actualGreetingText = greetingActions.getGreetingText();

        assertTrue(actualGreetingText.contains("Hello, " + testName + "!"),
                "Der abgerufene Text sollte 'Hello, " + testName + "!' enthalten, war aber: " + actualGreetingText);
    }

    @Test
    @DisplayName("E2E-NONFUNC-001: Should respond with HTTP 200 OK status code")
    void checkHttpResponseStatus() {

        com.microsoft.playwright.Response response = page.navigate("http://localhost:8080/greeting");

        assertTrue(response.ok(),
                "Expected HTTP status 200 OK, but received: " + response.status());
    }

    @Test
    @DisplayName("E2E-NONFUNC-002: Should load the content within the acceptable response time limit")
    void checkResponseTimePerformance() {

        GreetingActions greetingActions = new GreetingActions(page);

        long loadTime = greetingActions.measureLoadTime();

        assertTrue(loadTime <= MAX_LOAD_TIME_MS,
                "Endpoint loaded too slowly! Load time was " + loadTime + "ms, but max allowed is " + MAX_LOAD_TIME_MS
                        + "ms.");
    }
}