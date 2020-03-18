package edu.eci.airportfinder.cache;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class AirportsFinderCache {
	
	// ArrayList Tiempo[0] y Json[1]
	private ConcurrentHashMap<String, ArrayList<Object>> res = new ConcurrentHashMap<>();
	
	/**
	 * Revisa si han pasado 5 minutos desde que se guardo el cache y dice si hay o no hay cache.
	 * @param name Es el nombre de la llave en este caso de res a revisar si tiene cache.
	 * @return true, false dependiendo si tiene o no tiene cache.
	 */
	public boolean isThereCache(String name) {
		boolean isThereCache = false;
		if (res.get(name) != null && System.currentTimeMillis() - (long) res.get(name).get(0) <= 1000 * 60 * 5) {
			isThereCache = true;
		}
		return isThereCache;
	}
	
	/**
	 * Guarda el cache de una persona en un hashmap que tiene las propiedades de tener como llave la ciudad y como clave un arrayList con el tiempo y el json.
	 * @param name Es el nombre de la llave (ciudad a ser guardado).
	 * @param json Es el Json generado por la clase HttpConnectionServices a guardar.
	 */
	public void save(String name, String json) {
		//System.out.println("GUARDO");
		ArrayList<Object> tiempoJson = new ArrayList<>();
		tiempoJson.add(System.currentTimeMillis());
		tiempoJson.add(json);
		res.put(name, tiempoJson);
	}
	
	/**
	 * Da el cache de la llave (ciudad) deseada.
	 * @param name Es el nombre de la llave requerida.
	 * @return El cache (en esto caso la posición 1 del arrayList que es el json generado) de la llave (ciudad) deseada.
	 */
	public String getCache(String name) {
		//System.out.println("Entro Cache");
		return (String) res.get(name).get(1);
	}
	
}
