package model;

import java.util.Map;

public class Driver {
	public Integer id;
	public Double latitude;
	public Double longitude;
	public boolean available;
	
	public void populate(Map<String, Object> data) {
		this.id = ((Number) data.get("id")).intValue();
		this.latitude = (Double) data.get("latitude");
		this.longitude = (Double) data.get("longitude");
		this.available = (Boolean) data.get("available");
	}
}
