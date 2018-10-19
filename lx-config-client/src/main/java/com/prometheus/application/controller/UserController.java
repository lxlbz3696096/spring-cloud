package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Value("${spring.datasource.master.driver-class-name}")
    private String port;

    @GetMapping("/info")
    public String getProperties(){
        return "Info is-------" + port;
    }

}
