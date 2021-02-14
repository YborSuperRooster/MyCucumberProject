package com.yborsuperrooster.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Represents a Web Browser.
 */
@Component
public class Browser implements InitializingBean, DisposableBean {

    @Value("${selenium.browser.type:chrome}")
    private String browserType;

    private WebDriver driver;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (browserType.toLowerCase().equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void maxWindow() {
        driver.manage().window().maximize();
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public String title() {
        return driver.getTitle();
    }

    /**
     * The destroy() Spring lifecycle method hook closes the browser when the tests are complete.
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        driver.quit();
    }
}
