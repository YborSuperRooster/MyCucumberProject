package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.selenium.Browser;
import com.yborsuperrooster.selenium.page.CartPage;
import com.yborsuperrooster.selenium.page.ProductsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import static org.junit.Assert.*;

public class AddRemoveProductsStepDefinitions {

    @Autowired
    private Browser browser;

    @Autowired
    private ProductsPage productsPage;

    @Autowired
    private CartPage cartPage;

    @Before
    public void setup() {
        productsPage.setBrowser(browser);
        cartPage.setBrowser(browser);
    }

    @Given("^user is on the products page$")
    public void user_is_on_the_products_page() throws Throwable {
        productsPage.goTo();
        productsPage.isAt();
    }


    @When("user A adds {int} products to their cart")
    public void userAddsNumOfProductsToTheirCart(int num, DataTable products) {
        List<String> items = new ArrayList<>(products.asList(String.class));
        Collections.shuffle(items);
        for (int i = 0; i < num; i++) {
            productsPage.addToCart(items.get(i));
        }
    }

    @Then("cart badge displays {int} products")
    public void cart_badge_displays_number_of_products_added (int added) {
        assertTrue(productsPage.validateCartBadgeCount(added));
        productsPage.clearCart();
    }



    @Given("user B has {int} products to their cart")
    public void userHasAddedNumProductsToTheirCart(int num, DataTable products) {
        productsPage.goTo();
        List<String> itemsAdded = new ArrayList<>(products.asList(String.class));
        Collections.shuffle(itemsAdded);
        for (int i = 0; i < num; i++) {
            productsPage.addToCart(itemsAdded.get(i));
        }
    }

    @When("user Removes {int} products from their cart")
    public void userRemovesNumProductsFromTheirCart(int numRemoved) {
        productsPage.removeItemsFromCart(numRemoved);
    }

    @Then("cart badge number decrements {int} products")
    public void cartBadgeNumberDecrementsRemovedProducts(int numItemsRemaining) {
        assertTrue(productsPage.validateCartBadgeCount(numItemsRemaining));
        productsPage.clearCart();
    }

    @Given("user C adds {int} products to their cart")
    public void userCAddsNumProductsToTheirCart(int num, DataTable products) {
        productsPage.goTo();
        List<String> items = new ArrayList<>(products.asList(String.class));
        Collections.shuffle(items);
        for (int i = 0; i < num; i++) {
            productsPage.addToCart(items.get(i));
        }
    }

    @When("user clicks the cart icon")
    public void userClicksTheCartIcon() {
        productsPage.clickCartIcon();
    }

    @Then("they should see the same list of products on the Cart page")
    public void seeTheListOfProductsTheyAdded() {
        cartPage.continueShopping();
        ArrayList<String> prodPageList = productsPage.getProductsAddedToCart();
        cartPage.goTo();
        assertEquals(prodPageList, cartPage.getListOfAddedProducts());
        cartPage.clearCart();
    }

    @Given("user C adds <num> products to their cart")
    public void userCAddsNumProductsToTheirCart() {
    }
}
