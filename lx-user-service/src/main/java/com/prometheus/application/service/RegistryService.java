package com.prometheus.application.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.application.mapper.UserMapper;
import com.prometheus.application.model.User;

@Service
public class RegistryService {
	
	@Autowired
	UserMapper userMapper;
	
	public void userRegistry(String name, Integer age) {
		Random random = new Random();
		User user = new User();
		user.setAge(age);
		user.setName(name);
		user.setUserId(random.nextInt());
		userMapper.insertSelective(user);
	}

	public User userInfos(String name) {
		User user = userMapper.selectByName(name);
		return user;
	}

}
