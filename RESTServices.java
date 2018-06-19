package com.christ.service;

import org.glassfish.jersey.server.ResourceConfig;

public class RESTServices extends ResourceConfig {

	public RESTServices() {
		// Define the package which contains the service classes
		packages("com.fasterxml.jackson.jaxrs.json");
		packages("com.christ.service");
	}
}
