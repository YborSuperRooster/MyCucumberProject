package com.yborsuperrooster.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        features = {"classpath:features/"},
        glue = { "com.yborsuperrooster.cucumber.glue" })
public class SwagLabsTestSuite {
}
