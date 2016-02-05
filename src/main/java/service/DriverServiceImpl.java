package service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import model.Driver;
import repository.DriverRepository;

@Singleton
public class DriverServiceImpl implements DriverService{
	
	@Inject
	private DriverRepository repository;
		
	@Override
	public Driver save(Driver driver) {
		return this.repository.save(driver);
	}

	@Override
	public Driver get(Integer driverId) {
		return this.repository.get(driverId);
	}

	@Override
	public List<Driver> listAll() {
		return this.repository.listAll();
	}
	
}
