package edu.eci.airportfinder.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.airportfinder.services.AirportsFinderServices;

@RestController
@RequestMapping(value = "/airports")
public class AirportsFinderController {
	
	
	@Autowired 
	AirportsFinderServices airportsFinderServices;
	
	/**
	 * Retorna todos los aeropuertos de una ciudad
	 * @param name Es el nombre de la ciudad
	 * @return Los aeropuertos de la ciudad requerida
	 */
	@RequestMapping(value = "/{name}", method=RequestMethod.GET)
	public ResponseEntity<?> getAiportsByName(@PathVariable String name){
		return new ResponseEntity<>(airportsFinderServices.getAirportsByName(name),HttpStatus.ACCEPTED);
	}
}
