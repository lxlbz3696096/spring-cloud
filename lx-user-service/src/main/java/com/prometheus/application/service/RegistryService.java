package com.prometheus.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {
	
	@Value("${server.port}")
    String port;

	public String userRegistry(String name) {
		return "Welcome to use the service provided by Server:" + port;
	}

}
