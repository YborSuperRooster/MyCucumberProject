package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.selenium.page.ProductsPage;
import com.yborsuperrooster.selenium.page.SwagLabsLandingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertTrue;

public class LoginStepDefinitions {

    @Autowired
    SwagLabsLandingPage login;
    
    @Autowired
    ProductsPage products;

    @Given("launch Swaglabs application")
    public void launch_application() {
        login.goTo();
        assertTrue(login.isAt());
    }

    @When("user enters {word} and password to login")
    public void user_enters_username_and_password_to_login(String userName) {
        login.setUserId("user-name", userName)
                .setPassword("password", "secret_sauce")
                .submit("login-button");
    }

    @Then("Products page is populated")
    public void products_page_is_populated() {
        products.isAt();
    }

    @And("All Inventory items are displayed")
    public void inventory_items_are_displayed() {
        assertTrue(products.validateProducts(6));
    }

    @When("user enters invalid {word} and or invalid {word} to login")
    public void userEntersInvalidUsernameAndOrInvalidPasswordToLogin(String userName, String password) {
        login.setUserId("user-name", userName)
                .setPassword("password", password)
                .submit("login-button");
    }

    @Then("{} for invalid credentials is displayed")
    public void messageIsDisplayed(String message) {
        assertTrue(login.validate(message));
    }


    @When("user leaves {word} and or {word} field blank")
    public void userLeavesUsernameOrPasswordFieldBlank(String userName, String password) {
        if(userName.equals("blank")) {
            login.setUserId("user-name","");
            login.setPassword("password", password);
        }
        else if(password.equals("blank")) {
            login.setUserId("user-name", userName);
            login.setPassword("password", "");
        }
            login.submit("login-button");
    }

    @Then("{} for required field is displayed")
    public void messageForRequiredFieldDisplayed(String message) {
        assertTrue(login.validate(message));
    }

}


