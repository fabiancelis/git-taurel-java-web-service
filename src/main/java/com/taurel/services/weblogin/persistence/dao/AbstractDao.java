package com.taurel.services.weblogin.persistence.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.object.StoredProcedure;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import com.taurel.services.weblogin.message.request.RequestGeneric;
import com.taurel.services.weblogin.message.response.ResponseGeneric;

import resources.properties.ApplicationProperties;

public abstract class AbstractDao extends CommonsDao {
	
	private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);
	
	private ApplicationProperties propertyPlaceholderConfigurer;

	public ResponseGeneric getResponse(RequestGeneric request, StoredProcedure procedure) throws Exception{

		ResponseGeneric response = new ResponseGeneric();
	    
		BigDecimal codigo = new BigDecimal(0);
		String descripcion = "";
		String transaccion = null;
		String aplicacion = null;
		String origen = null;
		String plataforma = null;
		final String metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String dataSource = null;
		String storedProcedure = null;
		Monitor monitor = null;
		Map<String, Object> inParams = null;
		Map<String, Object> outParams = null;
		
		try {

			if (StringUtils.isBlank(request.getTransaccion())) {

				transaccion = generateTransactionId();
			} else {
				transaccion = request.getTransaccion();
			}
		    
		    aplicacion = ApplicationProperties.getProperty("aplicacion");

		    origen = this.getClass().getCanonicalName();

		    plataforma = ApplicationProperties.getProperty("platform");

	    	dataSource = ApplicationProperties.getProperty("datasource.jndiname.ds");
	    
	    	storedProcedure =  procedure.getSql();
		    		
		    monitor = MonitorFactory.start(origen);

		    inParams = getInputParameter(request);
		    		    		    
		    this.writeInParamTrace(inParams, LOGGER, transaccion, aplicacion, origen, metodo, plataforma, dataSource, storedProcedure);

		    outParams = procedure.execute(inParams);
		    
		    this.handleOutParameterCode(outParams, LOGGER);

	        response=this.processAndCreateResponse(outParams);

		    monitor.stop();

		    response.setTiempo(monitor.getLastValue());
		    
		    response.setTransaccion(transaccion);
		    
		    
		    if (response.getPropertyAsked("CD_ERROR") == null)
		    	codigo = null;
		    else
		    	codigo = BigDecimal.valueOf(Integer.parseInt(response.getPropertyAsked("CD_ERROR").toString()));
		    
		    if (response.getPropertyAsked("DE_ERROR") == null)
		    	descripcion = null;
		    else
		    	descripcion = (String) response.getPropertyAsked("DE_ERROR");

		    this.writeOutParamTrace(outParams,LOGGER, transaccion, aplicacion, origen, metodo, plataforma, dataSource, storedProcedure,
				    String.valueOf(codigo), descripcion, String.valueOf(monitor.getLastValue()));
		    

		} catch (Exception e) {
			e.printStackTrace();
		  throw  this.handleException(e, inParams, LOGGER, transaccion, aplicacion, origen, metodo, plataforma, dataSource, storedProcedure);
		}
				
		return response;
	}
	
    public Map<String, Object> getInputParameter(RequestGeneric request) {
        Map<String, Object> response=new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry:request.getMap().entrySet()){
            response.put(entry.getKey(), entry.getValue());
        }
        
        return response;
    }

    public ResponseGeneric processAndCreateResponse(Map<String, Object> outParams) {
            ResponseGeneric response=new ResponseGeneric();
            response.setMap(outParams);
            return response;
    }


	public ApplicationProperties getPropertyPlaceholderConfigurer() {
		return propertyPlaceholderConfigurer;
	}


	public void setPropertyPlaceholderConfigurer(
			ApplicationProperties propertyPlaceholderConfigurer) {
		this.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
	}
	
}
