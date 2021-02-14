package com.yborsuperrooster.cucumber.glue;

import com.yborsuperrooster.SpringConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringConfiguration.class)
@CucumberContextConfiguration
public class CucumberSpringConfig {
}
