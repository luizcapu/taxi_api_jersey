package com.taxi_api;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import repository.DriverRepository;
import repository.DriverRepositoryInMemory;
import service.DriverService;
import service.DriverServiceImpl;

public class AppBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		
		// services
		bind(DriverServiceImpl.class).to(DriverService.class).in(Singleton.class);
		
		// repositories
		bind(DriverRepositoryInMemory.class).to(DriverRepository.class).in(Singleton.class);
	}

}
