Feature: Checkout Step Two


  Scenario Outline: Item total is calculated
    Given user adds <num> products to their cart
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
    When user lands on the Checkout Overview Page
    Then the Item total should display the correct sum
    Examples:
      | num |
      | 2   |
      | 4   |
      | 6   |



