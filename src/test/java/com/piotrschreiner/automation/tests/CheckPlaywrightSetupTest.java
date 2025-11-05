package com.piotrschreiner.automation.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.piotrschreiner.automation.config.PlaywrightTestBase;

@DisplayName("E2E Setup Validation")
@Tag("E2E")
public class CheckPlaywrightSetupTest extends PlaywrightTestBase {

    @Test
    @DisplayName("E2E-SETUP-001: Should navigate to base URL and check the title")
    void checkPlaywrightConnection() {

        assertThat(page).hasURL("http://localhost:8080/greeting");

        assertThat(page.locator("body")).isVisible();
    }
}