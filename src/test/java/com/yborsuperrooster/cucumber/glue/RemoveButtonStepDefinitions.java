package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.selenium.page.ProductsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RemoveButtonStepDefinitions {

    @Autowired
    ProductsPage productsPage;

    @Given("^user has not added any products to cart and no Remove buttons display$")
    public void user_has_not_added_any_products_to_cart() {
        productsPage.goTo();
        Assert.assertFalse(productsPage.validateButtonText("REMOVE"));
    }

    @When("user clicks Add to Cart button for all Products")
    public void clicksAddToCartButtonForAProduct(DataTable products) {
        List<String> items = products.asList(String.class);
        for (String item : items) {
            productsPage.addToCart(item);
        }
    }

    @Then("remove button is displayed for all products")
    public void removeButtonIsDisplayed() {
        assertTrue(productsPage.validateButtonText("REMOVE"));
    }
}
