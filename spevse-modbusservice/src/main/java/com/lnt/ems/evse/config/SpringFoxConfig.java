/**
* EVSE is part of L&T SPARK Digital Energy Platform
* (c)2021-2024, L&T ECC (PT&D Digital Solutions, and its affiliates and assigns and licensors
* All rights reserved
* L&T Construction is a Parent Company of L&T PT&D Digital Solutions.
* No claim to copyright is made for original U.S. Government Works.
**/
package com.lnt.ems.evse.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringFoxConfig {

	 public static final String AUTHORIZATION_HEADER = "Authorization";
	    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
	
	   @Bean
	   public Docket productApi() { 
	      return new Docket(DocumentationType.SWAGGER_2)
	    		  .select()								
	    		  .apis(RequestHandlerSelectors.any())
	    		  .paths(PathSelectors.any())
	    		  .build()
	    		  .apiInfo(ApiInfo.DEFAULT)
	    		  .securityContexts(Lists.newArrayList(securityContext()))
	              .securitySchemes(Lists.newArrayList(apiKey()))
	              .useDefaultResponseMessages(false);
	   }
	   
	   
	   @Bean
	    public WebMvcConfigurer webMvcConfigurer()
	    {
	        return new WebMvcConfigurer()
	        {
	            @Override
	            public void addResourceHandlers( ResourceHandlerRegistry registry )
	            {
	                registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
	                registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
	            }
	        };
	    }
	   private ApiKey apiKey() {
	        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	    }

	    private SecurityContext securityContext() {
	        return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
	            .build();
	    }

	    List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope
	            = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Lists.newArrayList(
	            new SecurityReference("JWT", authorizationScopes));
	    }

}
