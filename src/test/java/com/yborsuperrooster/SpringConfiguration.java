package com.yborsuperrooster;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.yborsuperrooster")
@Configuration
@PropertySource("classpath:/application.properties")
public class SpringConfiguration { }