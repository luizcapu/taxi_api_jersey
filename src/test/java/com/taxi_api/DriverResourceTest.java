package com.taxi_api;


import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Driver;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import static org.junit.Assert.assertEquals;

public class DriverResourceTest {

    private HttpServer server;
    private WebTarget target;
	private static final String EMPTY_STR = "";
	private static final String PAYLOAD = "[{\"id\":1,\"latitude\":-20.123,\"longitude\":-22.432,\"available\":true},"
			+ "{\"id\":2,\"latitude\":-30.123,\"longitude\":-32.432,\"available\":false},"
			+ "{\"id\":3,\"latitude\":-40.123,\"longitude\":-42.432,\"available\":true},"
			+ "{\"id\":4,\"latitude\":-50.123,\"longitude\":-52.432,\"available\":true}]";
	private final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.createServer();
        server.start();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

	private static void compareDrivers(Driver d1, Driver d2) {
		assertEquals(d1.id, d2.id);
		assertEquals(d1.latitude, d2.latitude);
		assertEquals(d1.longitude, d2.longitude);
		assertEquals(d1.available, d2.available);
	}
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEndPointsFlow() {
    	Response r;
    	List<Driver> drivers;
    	
    	r = target.path("driver/1/status").request().get();
    	assertEquals(r.getStatus(), 204);

    	r = target.path("drivers").request().get();
    	drivers = r.readEntity(List.class);
    	assertEquals(r.getStatus(), 200);
    	assertEquals(drivers.size(), 0);
    	
    	List<Map<String, Object>> payloadList = gson.fromJson(PAYLOAD, List.class);
    	for (Map<String, Object> item : payloadList) {
    		Driver driver = new Driver();
        	driver.populate(item);

    		System.out.println("Testing driver " + driver.id);        	
    		
    		//-- BEGIN test insert 
    		r = target.path("driver/"+driver.id+"/status").request().put(Entity.entity(driver, MediaType.APPLICATION_JSON));
        	assertEquals(r.getStatus(), 200);
        	assertEquals(r.readEntity(String.class), EMPTY_STR);
    		//-- END test insert
        	
    		// BEGIN compare after insert
        	r = target.path("driver/"+driver.id+"/status").request().get();
        	assertEquals(r.getStatus(), 200);
        	DriverResourceTest.compareDrivers(driver, r.readEntity(Driver.class));
    		// END compare after insert

    		//-- BEGIN test update 
        	driver.latitude += 10;
        	driver.longitude += 5;
        	driver.available = !driver.available;
    		r = target.path("driver/"+driver.id+"/status").request().post(Entity.entity(driver, MediaType.APPLICATION_JSON));
        	assertEquals(r.getStatus(), 200);
        	assertEquals(r.readEntity(String.class), EMPTY_STR);
    		//-- END test update
        	
    		// BEGIN compare after update
        	r = target.path("driver/"+driver.id+"/status").request().get();
        	assertEquals(r.getStatus(), 200);
        	//DriverResourceTest.compareDrivers(driver, r.readEntity(Driver.class));
    		// END compare after update        	
    	}    	

		// BEGIN fetch drivers
		System.out.println("Fetching all drivers");        	
    	r = target.path("drivers").request().get();
    	drivers = r.readEntity(List.class);
    	assertEquals(r.getStatus(), 200);
    	assertEquals(drivers.size(), payloadList.size());
		// END fetch drivers    	
    }
}
