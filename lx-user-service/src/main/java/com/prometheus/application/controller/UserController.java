package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.application.model.TUser0;
import com.prometheus.application.service.RegistryService;

@RestController
public class UserController {

	@Autowired
	RegistryService registryService;
	
    @PostMapping("/regist")
    public String userRegistry(@RequestParam String name, @RequestParam Integer age) {
        registryService.userRegistry(name, age);
        return "Registry success";
    }
    
    @GetMapping("/user")
    public TUser0 user(@RequestParam String name){
    	TUser0 tUser0 = registryService.userInfos(name);
    	return tUser0;
    }
}
