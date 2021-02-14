package com.yborsuperrooster.cucumber.glue;
import com.yborsuperrooster.selenium.page.CartPage;
import com.yborsuperrooster.selenium.page.Page;
import com.yborsuperrooster.selenium.page.SwagLabsLandingPage;
import com.yborsuperrooster.selenium.menu.HamburgerMenu;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class HamburgerMenuStepDefinitions {
    @Autowired
    Page productsPage;

    @Autowired
    HamburgerMenu hamMenu;

    @Autowired
    SwagLabsLandingPage landingPage;

    @Autowired
    CartPage cartPage;

    @Given("^user navigates to the Cart Page$")
    public void user_is_on_the_cart_page() {
        cartPage.goTo();
        assertTrue(cartPage.isAt());
    }

    //shared "When" action between features
    @When("^user clicks the hamburger menu$")
    public void clicks_the_hamburger_menu() {
        hamMenu.clickHamburger();
    }

    @And("^clicks All Items$")
    public void clicks_all_items() {
        hamMenu.goTo(HamburgerMenu.Locations.AllItems);
    }

    @Then("^user is on the Products page$")
    public void user_is_on_products_page() {
        assertTrue(productsPage.isAt());
    }

    @Given("^user navigates to the Products page$")
    public void user_navigates_to_products_page() {
        productsPage.goTo();
        user_is_on_products_page();
    }

    @And("^clicks Logout$")
    public void clicks_logout() {
        hamMenu.goTo(HamburgerMenu.Locations.Logout);
    }

    @Then("^user is logged out$")
    public void user_is_logged_out() {
        landingPage.isAt();
    }
}
