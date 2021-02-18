package com.yborsuperrooster;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//Application configuration class(Config.java)
@ComponentScan("com.yborsuperrooster")
@Configuration
@PropertySource("classpath:/application.properties") //tells spring where to look for config values
public class SpringConfiguration { }
