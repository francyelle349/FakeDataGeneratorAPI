package com.FakeDataHub.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.FakeDataHub.demo.Service.RateLimit;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean<RateLimit> rateLimitingFilter() {
        FilterRegistrationBean<RateLimit> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimit());
        registrationBean.addUrlPatterns("/generate/*"); // Register filter for API endpoints
        return registrationBean;
    }
}
