package com.taxi_api.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Driver;

import org.glassfish.jersey.server.JSONP;

import service.DriverService;


@Path("driver/{driver_id}/status")
public class DriverController {
	
	private final DriverService service;

	@Inject
	public DriverController(DriverService driverService) {
		this.service = driverService;
	}
	
    @GET
    @JSONP
    @Produces(MediaType.APPLICATION_JSON)
    public Driver getById(@PathParam("driver_id") Integer driverId) {        
    	return this.service.get(driverId);
    }
    
    private String save(final Driver driver, boolean updating) {
    	this.service.save(driver);
    	return "";
    }
    
    @PUT
    @JSONP
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insert(@PathParam("driver_id") Integer driverId, final Driver driver) {        
    	driver.id = driverId;
    	return this.save(driver, false);
    }

    @POST
    @JSONP
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("driver_id") Integer driverId, final Driver driver) {        
    	//return new Driver();
    	driver.id = driverId;
    	return this.save(driver, true);
    }
    
}
