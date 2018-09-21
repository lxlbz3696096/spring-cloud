package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.application.clients.UserFeignClient;

@RestController
public class OrderController {

	@Autowired
	UserFeignClient userFeignClient;
	
	@GetMapping(value="/order")
	public String order(@RequestParam String name){
		return userFeignClient.getUserInfo(name);
	}
}
