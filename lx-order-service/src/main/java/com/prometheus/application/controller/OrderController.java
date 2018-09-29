package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.application.clients.UserFeignClient;
import com.prometheus.application.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	UserFeignClient userFeignClient;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(value="/order")
	public String order(@RequestParam String name){
		String userInfo = userFeignClient.getUserInfo(name);
		orderService.order();
		return "order success";
	}
}
