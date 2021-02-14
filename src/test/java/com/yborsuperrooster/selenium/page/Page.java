package com.yborsuperrooster.selenium.page;

import com.yborsuperrooster.selenium.Browser;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected Browser browser;
    protected WebDriverWait wait;

    public void setBrowser(Browser browser) {
        this.browser = browser;
        wait = new WebDriverWait(browser.getDriver(), 5);
    }

    public abstract void goTo();
    public abstract boolean isAt();
    public abstract String getPageTitle();
}

