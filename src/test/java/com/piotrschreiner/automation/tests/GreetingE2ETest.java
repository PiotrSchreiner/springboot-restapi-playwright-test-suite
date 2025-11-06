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

    @Test
    @DisplayName("E2E-GREET-002: Should check if the greeting content reflects the query parameter")
    void checkGreetingContentWithParameter() {

        // 1. Initialisierung der Actions-Schicht
        GreetingActions greetingActions = new GreetingActions(page);

        // Der Name, den wir übergeben
        String testName = "Automation";

        // 2. Aktion: Navigiere mit spezifischem Parameter
        greetingActions.navigateToGreetingWithName(testName);

        // 3. Aktion: Inhalt abrufen
        String actualGreetingText = greetingActions.getGreetingText();

        // 4. Assertion: Prüft, ob der erwartete Inhalt ("Hello, Automation!") enthalten
        // ist.
        assertTrue(actualGreetingText.contains("Hello, " + testName + "!"),
                "Der abgerufene Text sollte 'Hello, " + testName + "!' enthalten, war aber: " + actualGreetingText);
    }

}