package com.lnt.ems.evse.util;
//package com.lnt.ptd.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.web.servlet.config.annotation.CorsRegistration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class CorsUtil{
//	
//	@Autowired
//	private Environment env;
//	
//			 @Bean
//				public WebMvcConfigurer corsConfigurer() {
//					return new WebMvcConfigurer() {
//						@Override
//						public void addCorsMappings(CorsRegistry registry) {
//							CorsRegistration registration= registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
//						}
//					};
//			 }
//			 
//			
//}
