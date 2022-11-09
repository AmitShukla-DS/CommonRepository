package com.lnt.ems.evse.dataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private DataSourceInterceptor dataSourceInterceptor;

	
	// check data name form header for every request and choose it accordingly.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(dataSourceInterceptor).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
