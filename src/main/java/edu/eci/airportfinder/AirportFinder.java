package edu.eci.airportfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirportFinder {
	
	/**
	 * Funcion principal en la que se inyecta la clase AirportFinder
	 * @param args Es la lista de parametros recibida
	 */
	public static void main(String[] args) {
		SpringApplication.run(AirportFinder.class, args);
	}
}
