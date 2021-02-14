Feature: Application Login

  Background:
    Given launch Swaglabs application

  Scenario Outline: Home page default login
    When user enters <username> and password to login
    Then Products page is populated
    And All Inventory items are displayed
    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |

  Scenario Outline: Home page login error messages
    When user enters invalid <username> and or invalid <password> to login
    Then <message> for invalid credentials is displayed
    Examples:
      | username           | password     | message                                                     |
      | standard_user      | invalid      | Username and password do not match any user in this service |
      | invalid            | secret_sauce | Username and password do not match any user in this service |
      | locked_out_user    | secret_sauce | Sorry, this user has been locked out                        |


  Scenario Outline: Home page login error messages for null credentials
    When user leaves <username> and or <password> field blank
    Then <is required message> for required field is displayed
    Examples:
    | username      | password     | is required message  |
    | standard_user |   blank      | Password is required |
    |  blank        | secret_sauce | Username is required |
