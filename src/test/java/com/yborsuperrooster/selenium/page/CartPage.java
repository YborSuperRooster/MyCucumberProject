package com.yborsuperrooster.selenium.page;

import com.yborsuperrooster.selenium.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;

/**
 * CartPage represents the "Your Cart" page in Swag Labs (https://www.saucedemo.com/).
 */
@Component
public class CartPage extends Page {
    @Value("${selenium.page.cart.url}")
    private String url;

    @Value("${selenium.page.cart.title}")
    private String title;

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

    public ArrayList<String> getListOfAddedProducts() {
        String value;
        ArrayList<String> prodInCart = new ArrayList<>();
        wait.until(d -> d.findElements(By.className("inventory_item_name")));
        List<WebElement> productsAddedOnCartPage = browser.getDriver()
                .findElements(By.className("inventory_item_name"));

        for (WebElement element : productsAddedOnCartPage) {
            value = element.getText();
            prodInCart.add(value);
        }
        return prodInCart;
    }


    public void clearCart() {
        wait.until(d -> d.findElements(By.xpath("//*[@class='btn_secondary cart_button' and text()='REMOVE']")));
        List<WebElement> removeButtons = browser.getDriver()
                .findElements(By.xpath("//*[@class='btn_secondary cart_button' and text()='REMOVE']"));
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public void continueShopping() {
        try {
            wait.until(d -> d.findElements(By.xpath("//*[@class='btn_secondary' and text()='Continue Shopping']")));
            browser.getDriver().findElement(By.xpath("//*[@class='btn_secondary' and text()='Continue Shopping']")).click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found");
        }
    }

    public void checkout() {
        try {
            wait.until(d -> d.findElements(By.xpath("//*[@class='btn_action checkout_button' and text()='CHECKOUT']")));
            browser.getDriver().findElement(By.xpath("//*[@class='btn_action checkout_button' and text()='CHECKOUT']")).click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found");
        }
    }
}

