package com.piotrschreiner.automation.actions;

import com.microsoft.playwright.Page;
import com.piotrschreiner.automation.pages.GreetingPage;

public class GreetingActions extends BaseActions {

    private final GreetingPage greetingPage;

    public GreetingActions(Page page) {
        super(page);
        this.greetingPage = new GreetingPage(page);
    }

    public String getGreetingText() {
        return greetingPage.getBodyLocator().textContent();
    }
}