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
import java.util.List;

@Component
public class CheckoutPage extends Page {

    @Value("${selenium.page.checkout.url}")
    private String url;

    @Value("${selenium.page.checkout.title}")
    private String title;

    private String firstname;
    private String firstnameFieldname;
    private String lastname;
    private String lastnameFieldname;
    private String zipcode;
    private String zipcodeFieldname;

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

    public CheckoutPage setFirstName(String fieldname, String value) {
        this.firstnameFieldname = fieldname;
        this.firstname = value;
        return this;
    }

    public CheckoutPage setLastName(String fieldname, String value) {
        this.lastnameFieldname = fieldname;
        this.lastname = value;
        return this;
    }

    public CheckoutPage setZip(String fieldname, String value) {
        this.zipcodeFieldname = fieldname;
        this.zipcode = value;
        return this;
    }

    public CheckoutPage submit() {
        wait.until(d -> d.findElement(By.id(this.firstnameFieldname)));
        browser.getDriver().findElement(By.id(this.firstnameFieldname)).sendKeys(this.firstname);
        browser.getDriver().findElement(By.id(this.lastnameFieldname)).sendKeys(this.lastname);
        browser.getDriver().findElement(By.id(this.zipcodeFieldname)).sendKeys(this.zipcode);
        browser.getDriver().findElement(By.xpath("//input[@value='CONTINUE']")).click();
        return this;
    }

    public Double getItemTotal() {
        try {
            String value;
            double total;
            wait.until(d -> d.findElement(By.xpath("//div[@class='summary_subtotal_label']")));
            value = browser.getDriver().findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().substring(13);
            total = Double.parseDouble(value);
            return total;

        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }
        return null;
    }

    public Double calculateSumOfItems() {
        String value;
        double total = 0;
        wait.until(d -> d.findElements(By.xpath("//div[@class='inventory_item_price']")));
        List<WebElement> itemPrices = browser.getDriver()
                .findElements(By.xpath("//div[@class='inventory_item_price']"));

        for (WebElement element : itemPrices) {
            value = element.getText().substring(1);
            total += Double.parseDouble(value);
        }
        return total;
    }

    public Double getTax() {
        try {
            String value;
            double tax;
            wait.until(d -> d.findElement(By.xpath("//div[@class='summary_tax_label']")));
            value = browser.getDriver().findElement(By.xpath("//div[@class='summary_tax_label']")).getText().substring(6,10);
            tax = Double.parseDouble(value);
            return tax;

        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }
        return null;
    }


    public void clickCartIcon() {
        try {
            wait.until(d -> d.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")));
            browser.getDriver().findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element not found");
        }
    }
}




