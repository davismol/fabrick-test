package com.fabrick.test.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class SetRequestHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Value("${header.auth}")
	private String authHeaderValue;
    
    @Value("${header.apikey}")
	private String apyKeyValue;
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		request.getHeaders().set("Auth-Schema", authHeaderValue);
		request.getHeaders().set("Api-Key", apyKeyValue);
		request.getHeaders().set("X-Time-Zone", "Europe/Rome");
		
		return execution.execute(request, body);
	}

}
