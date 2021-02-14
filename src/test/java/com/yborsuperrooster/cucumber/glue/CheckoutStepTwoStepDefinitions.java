package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.selenium.page.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutStepTwoStepDefinitions {
    @Autowired
    ProductsPage productsPage;

    @Autowired
    CartPage cartPage;

    @Autowired
    CheckoutPage checkOut;

    @Given("user adds {int} products to their cart")
    public void userAddsProductsToTheirCart(int num, DataTable products) {
        productsPage.goTo();
        List<String> items = new ArrayList<>(products.asList(String.class));
        Collections.shuffle(items);
        for (int i = 0; i < num; i++) {
            productsPage.addToCart(items.get(i));
        }
    }

    @When("^user lands on the Checkout Overview Page$")
    public void they_land_on_the_checkout_overview_page()  {
        productsPage.clickCartIcon();
        cartPage.checkout();
        checkOut.setFirstName("first-name","Homer");
        checkOut.setLastName("last-name","Simpson");
        checkOut.setZip("postal-code","45038");
        checkOut.submit();
        checkOut.isAt();
    }

    @Then("^the Item total should display the correct sum$")
    public void the_item_total_sum_should_display()  {
        Assert.assertEquals(checkOut.getItemTotal(),checkOut.calculateSumOfItems());
        checkOut.clickCartIcon();
        cartPage.clearCart();

    }

    //TODO: Remove commented out code
    /*@And("a 0.08% tax should be calculated based on the Item total sum")
    public void theTaxTotalShouldDisplayTheCorrectAmount() {

    }*/
}
