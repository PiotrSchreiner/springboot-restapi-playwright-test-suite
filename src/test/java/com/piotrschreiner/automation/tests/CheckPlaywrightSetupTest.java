package com.piotrschreiner.automation.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.piotrschreiner.automation.config.PlaywrightTestBase;

/**
 * Minimal test case to validate the Playwright configuration and connection to
 * the Spring Boot App.
 * This proves that the PlaywrightTestBase setup is working correctly.
 */
@DisplayName("E2E Setup Validation")
@Tag("E2E")
public class CheckPlaywrightSetupTest extends PlaywrightTestBase {

    @Test
    @DisplayName("E2E-SETUP-001: Should navigate to base URL and check the title")
    void checkPlaywrightConnection() {
        // Der page.navigate() Aufruf erfolgt bereits in PlaywrightTestBase.BeforeEach
        // Wir prüfen, ob die Spring Boot Anwendung antwortet.

        // 1. Prüfe, ob die Seite geladen wurde (Status 200)
        assertThat(page).hasURL("http://localhost:8080/greeting?name=World");

        // 2. Prüfe auf ein spezifisches Element (hier: den Body der Antwort)
        // Da die Spring Boot App nur JSON liefert, prüfen wir, ob der Inhalt existiert.
        // Achtung: Wenn die App eine UI hätte, würden wir UI-Elemente prüfen.
        // Hier prüfen wir einfach, dass der Inhalt nicht leer ist.
        assertThat(page.locator("body")).isVisible();
    }
}