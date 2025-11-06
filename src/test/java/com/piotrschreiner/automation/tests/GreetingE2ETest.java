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

    @Test
    @DisplayName("E2E-GREET-001: Should check if the default greeting content is present")
    void checkDefaultGreetingContent() {

        GreetingActions greetingActions = new GreetingActions(page);

        String actualGreetingText = greetingActions.getGreetingText();

        assertTrue(actualGreetingText.contains("Hello, World!"),
                "Der abgerufene Text sollte 'Hello, World!' enthalten, war aber: " + actualGreetingText);
    }
}