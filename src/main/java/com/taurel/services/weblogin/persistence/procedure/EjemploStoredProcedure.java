package com.taurel.services.weblogin.persistence.procedure;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.taurel.services.weblogin.persistence.mapper.JsonRowMapper;

public class EjemploStoredProcedure extends StoredProcedure {

	public EjemploStoredProcedure(JdbcTemplate jdbcTemplate, String name) {
		super(jdbcTemplate, name);
		
		this.declareParameter(new SqlParameter("PI_EJEMPLO", Types.VARCHAR));
		
		this.declareParameter(new SqlReturnResultSet("PI_OUT_JSON", new JsonRowMapper()));
		
		this.declareParameter(new SqlOutParameter("CD_ERROR", Types.INTEGER));
		this.declareParameter(new SqlOutParameter("DE_ERROR", Types.VARCHAR));
	}

}
