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
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsPage extends Page {

    @Value("${selenium.page.products.url}")
    private String url;

    @Value("${selenium.page.products.title}")
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

    public void addToCart(String item) {
        wait.until(d -> d.findElement(By.linkText(item)));
        browser.getDriver().findElement(By.linkText(item));
        browser.getDriver().findElement(By.xpath("//button[text()='ADD TO CART']")).click();
    }

    public void removeItemsFromCart(int numRemoved) {
        int x = 0;
        wait.until(d -> d.findElements(By.xpath("//*[@class='btn_secondary btn_inventory' and text()='REMOVE']")));
        List<WebElement> removeButtons = browser.getDriver()
                .findElements(By.xpath("//*[@class='btn_secondary btn_inventory' and text()='REMOVE']"));
        for (WebElement removeButton : removeButtons) {
            if (x < numRemoved) {
                removeButton.click();
                x++;
            }
        }
    }

    public void clearCart() {
        wait.until(d -> d.findElements(By.xpath("//*[@class='btn_secondary btn_inventory' and text()='REMOVE']")));
        List<WebElement> removeButtons = browser.getDriver()
                .findElements(By.xpath("//*[@class='btn_secondary btn_inventory' and text()='REMOVE']"));
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public void clickCartIcon() {
        try {
            wait.until(d -> d.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")));
            browser.getDriver().findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found");
        }
    }

    public boolean validateProducts(int numOfProducts) {
        wait.until(d -> d.findElements(By.className("inventory_item")));
        List<WebElement> listOfProducts = browser.getDriver()
                .findElements(By.className("inventory_item"));
        return listOfProducts.size() == numOfProducts;
    }

    public ArrayList<String> getProductsAddedToCart() {
        String value;
        ArrayList<String> prodAddedToCart = new ArrayList<>();
        wait.until(d -> d.findElements(By.xpath
                ("//*[@class='inventory_item_name']//*[@class='btn_secondary btn_inventory' and text()='REMOVE']")));
        List<WebElement> productsAdded = browser.getDriver().findElements(By.xpath
                ("//button[@class='btn_secondary btn_inventory' and text()='REMOVE']/preceding::a/div[@class='inventory_item_name']"));

        for (WebElement element : productsAdded) {
            value = element.getText();
                 prodAddedToCart.add(value);
                } return prodAddedToCart;
        }

    public boolean validateButtonText(String buttonText) {
        try {
            wait.until(d -> d.findElement(By.xpath(String.format
                    ("//button[@class='btn_secondary btn_inventory' and text()='%s']", buttonText))));
            browser.getDriver().findElements(By.xpath(String.format
                    ("//button[@class='btn_secondary btn_inventory' and text()='%s']", buttonText)));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }

    }

    public boolean validateCartBadgeCount(int badgeCount) {
        try {
            wait.until(d -> d.findElement(By.xpath(String.format
                    ("//span[@class='fa-layers-counter shopping_cart_badge' and text()='%s']", badgeCount))));
            browser.getDriver().findElement(By.xpath(String.format
                    ("//span[@class='fa-layers-counter shopping_cart_badge' and text()='%s']", badgeCount)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed(String className) {
        try {
            wait.until(d -> d.findElement(By.className(className)));
            browser.getDriver().findElement(By.className(className));
            return true;
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found");
        }
        return false;
    }
}

