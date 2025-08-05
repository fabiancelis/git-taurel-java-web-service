package com.taurel.services.weblogin.rest;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taurel.services.weblogin.message.request.RequestGeneric;
import com.taurel.services.weblogin.message.response.ResponseGeneric;
import com.taurel.services.weblogin.persistence.dao.EjemploConsultaDao;

@Path("/ejemplo")
@RestController
public class EjemploResource {

	@Autowired
	private EjemploConsultaDao ejemploConsultaDao;
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/datos")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getDatos(String requestJson) throws Exception {
		
		JSONObject jsonObject = new JSONObject(requestJson);
		
		RequestGeneric request= new RequestGeneric();
		request.setPropertyTobeAsked("PI_EJEMPLO", jsonObject.has("ejemplo_parametro_entrada") ? jsonObject.getString("ejemplo_parametro_entrada") : null);
		
		ResponseGeneric response = ejemploConsultaDao.consultarJson(request);
		
		
		jsonObject.put("ejemplo_parametro_salida", ((ArrayList<String>)response.getPropertyAsked("PI_OUT_JSON")).get(0));
		
		return Response     
		        .status(Status.OK)
		        .entity(jsonObject)
		        .build(); 
	}
}
