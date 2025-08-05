/**
 * 
 */
 
package com.taurel.services.weblogin.message.request;

import java.util.HashMap;
import java.util.Map;




/**
 * <p>
 * Clase que representa un request generico que sera usado en todas las transacciones
 * como proyecto piloto y contiene todos los objetos en un mapa, el cual tendra un metodo
 * donde se almacenen las propiedades y se lean por nombre. si la propiedad no esta presente
 * genera un valor nulo.
 * </p>
 * 
 * @version 1.0
 */

public class RequestGeneric extends RequestTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Map<String,Object> map;
	
	public RequestGeneric(){
		this.setMap(new HashMap<String,Object>());
	}

	/**
	 * @return the map
	 */
	public Map<String,Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	
	/**
	 * metodo que es usada para almacenar la propiedad que sera usuada en los PL/SQL como parametro y puede
	 * contener cualquier contidad de estos parametros, solo se almacena el nombre del objeto y la referencia
	 * al mismo objeto que luego sera recuperado por las implementacion del DAO en la consulta
	 * @param propertyName
	 * @param property
	 */
	
	public void setPropertyTobeAsked(String propertyName,Object property){
		this.getMap().put(propertyName, property);
	}
	
	/**
	 * 
	 * @param propertyName
	 * @return
	 */
	
	public Object getPropertyTobeAsked(String propertyName){
		return this.getMap().get(propertyName);
	}

}
