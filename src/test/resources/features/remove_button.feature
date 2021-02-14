Feature: Remove buttons display for items added to cart

  Scenario: Remove buttons display
    Given user has not added any products to cart and no Remove buttons display
    When user clicks Add to Cart button for all Products
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
    Then remove button is displayed for all products
