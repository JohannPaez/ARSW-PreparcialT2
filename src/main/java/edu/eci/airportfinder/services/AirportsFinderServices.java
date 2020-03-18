package edu.eci.airportfinder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.airportfinder.cache.AirportsFinderCache;
import edu.eci.airportfinder.http.HttpConnectionServices;

@Service
public class AirportsFinderServices {
	
	@Autowired 
	HttpConnectionServices servicesHttp;
	
	@Autowired
	AirportsFinderCache cache;
	
	/**
	 * Mire en cache si tiene guardado los aeropuertos de esa ciudad, sino consume la api para que le retorne esta información. 
	 * @param name Es el nombre de la ciudad.
	 * @return Los aeropuertos de la ciudad dada.
	 */
	public String getAirportsByName(String name) {
		String airports = null;
		if (cache.isThereCache(name)) {
			airports = cache.getCache(name);
		} else {
			airports = servicesHttp.getAirportsByName(name);
			String Json = servicesHttp.getAirportsByName(name);
			cache.save(name, Json);
		}
		
		return airports;
	}
	
	
}
