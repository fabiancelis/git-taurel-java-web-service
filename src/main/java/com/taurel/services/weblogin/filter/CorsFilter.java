package com.taurel.services.weblogin.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CorsFilter implements ContainerResponseFilter {
	
	Logger logger = LoggerFactory.getLogger(CorsFilter.class);
 
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    	logger.info("CorsFilter");
    	
    	requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    	requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    	requestContext.getHeaders().add("Access-Control-Allow-Headers","Origin, Content-Type, Accept, Authorization, apiKey");
    	requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    	responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    	responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    	responseContext.getHeaders().add("Access-Control-Allow-Headers","Origin, Content-Type, Accept, Authorization, apiKey");
    	responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
    }
    
}