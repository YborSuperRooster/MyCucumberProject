package com.yborsuperrooster.selenium.menu;

import com.yborsuperrooster.selenium.Browser;
import com.yborsuperrooster.selenium.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Location {

    private final WebDriverWait wait;
    private final Page clickResultPage;

    public Location(Browser browser, Page clickResultPage) {
        this.wait = new WebDriverWait(browser.getDriver(), 5);
        this.clickResultPage = clickResultPage;
    }

    public Page click(String linkId) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(linkId)));
        webElement.click();
        return clickResultPage;
    }
}
