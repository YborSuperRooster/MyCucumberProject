Feature: Adding and removing product items to/from cart



  Scenario Outline: Cart badge count increments
    Given user is on the products page
    When user A adds <num> products to their cart
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
    Then cart badge displays <added> products
    Examples:
      | num | added |
      | 5   | 5     |
      | 3   | 3     |
      | 2   | 2     |


  Scenario Outline: Cart badge number decrements
    Given user B has <num> products to their cart
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
    When user Removes <numRemoved> products from their cart
    Then cart badge number decrements <numItemsRemaining> products
    Examples:
      | num | numRemoved | numItemsRemaining |
      | 6   | 2          | 4                 |
      | 5   | 3          | 2                 |
      | 3   | 2          | 1                 |


  Scenario Outline: Products Added display on Cart Page
    Given user C adds <num> products to their cart
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
    When user clicks the cart icon
    Then they should see the same list of products on the Cart page
    Examples:
      | num |
      | 2   |
      | 4   |
      | 6   |
