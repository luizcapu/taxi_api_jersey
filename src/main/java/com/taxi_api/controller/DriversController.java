package com.taxi_api.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Driver;

import org.glassfish.jersey.server.JSONP;

import service.DriverService;


@Path("drivers")
public class DriversController {
	
	private final DriverService service;

	@Inject
	public DriversController(DriverService driverService) {
		this.service = driverService;
	}
	
    @GET
    @JSONP
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> listAll() {        
    	return this.service.listAll();
    }
    
}
