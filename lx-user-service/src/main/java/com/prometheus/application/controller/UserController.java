package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.application.service.RegistryService;

@RestController
public class UserController {

	@Autowired
	RegistryService registryService;
	
    @PostMapping("/regist")
    public String userRegistry(@RequestParam String name) {
        return registryService.userRegistry(name);
    }
    
    @GetMapping("/user")
    public String user(@RequestParam String name){
    	return "User info is name: "+name;
    }
}
