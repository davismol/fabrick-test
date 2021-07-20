package com.fabrick.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fabrick.test.interceptor.SetRequestHeaderInterceptor;

@Configuration
public class RestTemplateConfig {

	@Autowired
	private SetRequestHeaderInterceptor headerInterceptor;
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(headerInterceptor);
		return restTemplate;
	}
}