/**
 * 
 */
package com.taurel.services.weblogin.message.response;


import java.util.HashMap;
import java.util.Map;




/**
 * <p>
 * Clase que hace la representacion de una respuesta a una transaccion, esta clase contiene un mapa donde se tiene
 * la informacion de los objetos que se han consultado en base de datos y estan almacenados en dicho mapa.
 * </p>
 * 
 * @version 1.0
 */

public class ResponseGeneric extends ResponseTo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map;
	
	public ResponseGeneric(){
		this.setMap(new HashMap<String, Object>());
	}
	
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	/**
	 * metodo que es usada para almacenar la propiedad que fue respondia por un procedimiento PL/SQL puede
	 * contener cualquier contidad de estos parametros, solo se almacena el nombre del objeto y la referencia
	 * al mismo objeto que luego sera recuperado por las implementacion del DAO en la consulta
	 * @param propertyName
	 * @param property
	 */
	
	public void setPropertyAsked(String propertyName,Object property){
		this.getMap().put(propertyName, property);
	}
	
	/**
	 * Metodo que obtiene la propiedad que fue obtenida en una transaccion por un procedimiento PL/SQL
	 * 
	 * @param propertyName
	 * @return
	 */
	
	public Object getPropertyAsked(String propertyName){
		return this.getMap().get(propertyName);
	}

}
