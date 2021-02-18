package com.yborsuperrooster.selenium.page;

import com.yborsuperrooster.selenium.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SwagLabsLandingPage extends Page {

    @Value("${selenium.page.swag_labs.url}")
    private String url;

    @Value("${selenium.page.swag_labs.title}")
    private String title;

    private String userid;
    private String userIdFieldname;
    private String password;
    private String passwordFieldname;

    @Autowired
    @Override
    public void setBrowser(Browser browser) {
        super.setBrowser(browser);
    }

    @Override
    public void goTo() {
        browser.goTo(url);
        browser.maxWindow();
    }

    @Override
    public boolean isAt() {
        return browser.title().equals(title);
    }

    @Override
    public String getPageTitle() {
        return browser.getDriver().getTitle();
    }

    public SwagLabsLandingPage setUserId(String fieldname, String userName) {
        this.userIdFieldname = fieldname;
        this.userid = userName;
        return this;
    }

    public SwagLabsLandingPage setPassword(String fieldname, String value) {
        this.passwordFieldname = fieldname;
        this.password = value;
        return this;
    }

    public SwagLabsLandingPage submit(String id) {
        wait.until(d -> d.findElement(By.id(this.userIdFieldname)));
        browser.getDriver().findElement(By.id(this.userIdFieldname)).sendKeys(this.userid);
        browser.getDriver().findElement(By.id(this.passwordFieldname)).sendKeys(this.password);
        browser.getDriver().findElement(By.id(id)).click();
        return this;
    }

    public boolean validate(String message) {
        try {
            wait.until(d ->d.findElement(By.xpath(String.format("//*[contains(normalize-space(.),'%s')]", message))));  //(.) the value of the current node
            browser.getDriver().findElement(By.xpath(String.format("//*[contains(normalize-space(.),'%s')]", message)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
