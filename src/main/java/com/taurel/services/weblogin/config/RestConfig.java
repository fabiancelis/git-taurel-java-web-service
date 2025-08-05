package com.taurel.services.weblogin.config;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.taurel.services.weblogin.filter.CorsFilter;
import com.taurel.services.weblogin.rest.EjemploResource;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

@Component
@ApplicationPath("/resources")
@Path("/resources")
public class RestConfig extends ResourceConfig {

	public RestConfig(@Context ServletContext context) {
        WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(context);
        
        register(OpenApiResource.class);

         register(appCtx.getBean(EjemploResource.class));
         register(MOXyJsonProvider.class);
         register(CorsFilter.class);
    }
	
	
}
