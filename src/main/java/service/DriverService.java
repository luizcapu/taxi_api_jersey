package service;

import java.util.List;

import model.Driver;


public interface DriverService {
	
	public Driver save(Driver driver);

	public Driver get(Integer driverId);

	public List<Driver> listAll();
	
}
