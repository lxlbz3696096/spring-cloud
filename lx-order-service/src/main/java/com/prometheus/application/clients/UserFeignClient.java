package com.prometheus.application.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="user-service")
public interface UserFeignClient {
	
	@GetMapping(value="/user")
	String getUserInfo(@RequestParam(value="name") String name);
}
