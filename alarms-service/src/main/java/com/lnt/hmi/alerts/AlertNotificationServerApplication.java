package com.lnt.hmi.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableRetry
@EnableEurekaClient
public class AlertNotificationServerApplication {

	public static void main(String[] args) {
		System.setProperty("server.connection-timeout", "300000");
		SpringApplication.run(AlertNotificationServerApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
