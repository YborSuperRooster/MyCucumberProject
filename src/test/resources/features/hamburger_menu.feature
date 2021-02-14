Feature: HamburgerMenu


    Scenario: Hamburger menu All items
      Given user navigates to the Cart Page
      When user clicks the hamburger menu
      And clicks All Items
      Then user is on the Products page


    Scenario: Hamburger menu logout
      Given user navigates to the Products page
      When user clicks the hamburger menu
      And clicks Logout
      Then user is logged out
