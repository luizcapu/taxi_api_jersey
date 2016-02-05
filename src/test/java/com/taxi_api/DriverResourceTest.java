package com.taxi_api;

import java.util.List;

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

import static org.junit.Assert.assertEquals;

public class DriverResourceTest {

    private HttpServer server;
    private WebTarget target;
	private static final String EMPTY_STR = "";
	private static final String PAYLOAD = "[{\"id\":1,\"latitude\":-20.123,\"longitude\":-22.432,\"available\":true},"
			+ "{\"id\":2,\"latitude\":-30.123,\"longitude\":-32.432,\"available\":false},"
			+ "{\"id\":3,\"latitude\":-40.123,\"longitude\":-42.432,\"available\":true},"
			+ "{\"id\":4,\"latitude\":-50.123,\"longitude\":-52.432,\"available\":true}]";

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.createServer();
        server.start();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testEndPointsFlow() {
    	Response r;
    	List<Driver> drivers;
    	Driver driver;
    	
    	r = target.path("driver/1/status").request().get();
    	assertEquals(r.getStatus(), 204);

    	r = target.path("drivers").request().get();
    	drivers = r.readEntity(List.class);
    	assertEquals(r.getStatus(), 200);
    	assertEquals(drivers.size(), 0);
    	
    	r = target.path("driver/1/status").request().post(Entity.entity(new Driver(), MediaType.APPLICATION_JSON));
    	assertEquals(r.getStatus(), 200);
    	assertEquals(r.readEntity(String.class), EMPTY_STR);
    }
}
