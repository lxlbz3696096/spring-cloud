package com.prometheus.application.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.application.mapper.TUser0Mapper;
import com.prometheus.application.model.TUser0;

@Service
public class RegistryService {
	
	@Autowired
	TUser0Mapper tUser0Mapper;
	
	public void userRegistry(String name, Integer age) {
		Random random = new Random();
		TUser0 tUser0 = new TUser0();
		tUser0.setAge(age);
		tUser0.setName(name);
		tUser0.setUserId(random.nextInt());
		tUser0Mapper.insertSelective(tUser0);
	}

	public TUser0 userInfos(String name) {
		TUser0 tUser0 = tUser0Mapper.selectByName(name);
		return tUser0;
	}

}
