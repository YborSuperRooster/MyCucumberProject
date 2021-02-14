package com.yborsuperrooster.selenium.menu;

import com.yborsuperrooster.selenium.Browser;
import com.yborsuperrooster.selenium.page.Page;
import com.yborsuperrooster.selenium.page.ProductsPage;
import com.yborsuperrooster.selenium.page.SwagLabsLandingPage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HamburgerMenu implements Navigable {

    @Autowired
    SwagLabsLandingPage swagLabsLandingPage;

    @Autowired
    ProductsPage productsPage;

    @Autowired
    Browser browser;

    public enum Locations {
        AllItems("logout_sidebar_link"), Logout("logout_sidebar_link");

        private final String itemId;

        Locations(String itemId) {
            this.itemId = itemId;
        }
    }

    @Override
    public Page goTo(Enum name) {
        Locations locationValue = (Locations)name;
        Location location = new Location(
                this.browser,
                switch (locationValue) {
                    case Logout -> swagLabsLandingPage;
                    case AllItems -> productsPage;
                }
        );
        return location.click(locationValue.itemId);
    }

    public HamburgerMenu clickHamburger() {
        browser.getDriver().findElement(By.className("bm-burger-button")).click();
        return this;
    }
}
