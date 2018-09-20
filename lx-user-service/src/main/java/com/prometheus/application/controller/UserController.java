package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.application.service.RegistryService;

@RestController
public class UserController {

	@Autowired
	RegistryService registryService;
	
    @RequestMapping("/regist")
    public String userRegistry(@RequestParam String name) {
        return registryService.userRegistry(name);
    }
}
