package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.SpringConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;


/**
 * This class makes Cucumber aware of the SpringConfiguration.java class.
 */
@ContextConfiguration(classes = SpringConfiguration.class)
@CucumberContextConfiguration
public class CucumberSpringConfig {
}
