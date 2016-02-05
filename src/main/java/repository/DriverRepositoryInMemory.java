package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import model.Driver;

@Singleton
public class DriverRepositoryInMemory implements DriverRepository {
	
	private final Map<Integer, Driver> database = new HashMap<Integer, Driver>();
	
	@Override
	public Driver save(Driver driver) {
		this.database.put(driver.id, driver);
		return driver;
	}

	@Override
	public Driver get(Integer driverId) {
		return this.database.get(driverId);
	}

	@Override
	public List<Driver> listAll() {
		List<Driver> drivers = new ArrayList<Driver>();
		
		for (Driver driver : this.database.values()) {
			drivers.add(driver);
		}
		
		return drivers;
	}	
	
}
