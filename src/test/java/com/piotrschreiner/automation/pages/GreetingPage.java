package com.piotrschreiner.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GreetingPage extends BasePage {

    private final Locator bodyLocator = page.locator("body");

    public GreetingPage(Page page) {
        super(page);
    }

    public Locator getBodyLocator() {
        return bodyLocator;
    }
}