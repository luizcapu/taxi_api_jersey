package repository;

import java.util.List;

import model.Driver;

public interface DriverRepository {
	
	public Driver save(Driver driver);
	public Driver get(Integer driverId);
	public List<Driver> listAll();
	
}
