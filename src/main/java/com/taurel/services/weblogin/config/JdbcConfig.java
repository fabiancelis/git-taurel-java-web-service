package com.taurel.services.weblogin.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.grupodgfarm.commons.resources.properties.ApplicationProperties;
import com.taurel.services.weblogin.persistence.procedure.EjemploStoredProcedure;

@Configuration
@ComponentScan(basePackages = { "com.taurel.services.weblogin.rest",
								"com.taurel.services.weblogin.persistence.dao"
})
public class JdbcConfig {

	@Bean
    public DataSource dataSource() {
		JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
        dataSource.setJndiName("java:/TestSqlDS");
        try {
            dataSource.afterPropertiesSet();
        } catch (IllegalArgumentException | NamingException e) {
            throw new RuntimeException(e);
        }
        
        return (DataSource)dataSource.getObject();
    }
	
	@Bean(value = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		
		return jdbcTemplate;
	}
	
	@Bean(value = "propertyPlaceholderConfigurer")
	public ApplicationProperties applicationProperties() {
		ApplicationProperties app = new ApplicationProperties();
		Resource resource = new ClassPathResource("/resources/properties/application.properties");
		app.setLocation(resource);
		return app;
	}
	
	@Bean(value = "ejemploStoredProcedure")
	public EjemploStoredProcedure autorizaStoredProcedure() {
		
		EjemploStoredProcedure sp = new EjemploStoredProcedure(jdbcTemplate(), "P_EJEMPLO_STORED_PROCEDURE");
		
		return sp;
		
	}
	
}
