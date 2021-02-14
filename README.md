<img alt="Cucumber BDD Testing" src="cucumber.png" height="128px"/>

### My Cucumber Project
_by Erin Chancey (aka the Ybor Super Rooster)_

This project demonstrates some tests against Swag Labs (https://www.saucedemo.com/) using the following technologies.
* Spring Test Context
* JUnit5
* Cucumber
* Selenium

#### Usage

To execute all the tests included in this project, type `gradlew test`. 

The tests support Chrome or Firefox browsers via the Selenium Web Drivers. One or both of those Web Drivers must be 
available on the system in which the tests are being executed. It is assumed that they are configured in properties
(e.g. JAVA_TOOL_OPTIONS).

**ChangingThe Browser Type Under Test**

If no browser type is specified, then Chrome will be selected as the default browser. To switch the browser type to
FireFox, the property `selenium.browser.type` can be specified as follows.

`gradlew test -Dselenium.browser.type=firefox`

