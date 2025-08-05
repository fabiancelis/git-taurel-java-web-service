package com.taurel.services.weblogin.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taurel.services.weblogin.message.request.RequestGeneric;
import com.taurel.services.weblogin.message.response.ResponseGeneric;
import com.taurel.services.weblogin.persistence.procedure.EjemploStoredProcedure;

@Repository(value = "ejemploConsultaDao")
public class EjemploConsultaDao extends AbstractDao {

	@Autowired
	private EjemploStoredProcedure ejemploStoredProcedure;
	
	public ResponseGeneric consultarJson(RequestGeneric request) throws Exception {
		
		return getResponse(request, ejemploStoredProcedure);
		
	}
}
