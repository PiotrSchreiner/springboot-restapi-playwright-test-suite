package com.piotrschreiner.automation.actions;

import com.microsoft.playwright.Page;

public abstract class BaseActions {

    protected final Page page;

    public BaseActions(Page page) {
        this.page = page;
    }
}