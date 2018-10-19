package com.prometheus.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Value("${server.application}")
    private String application;

    @GetMapping("/getProperties")
    public String getProperties(){
        return "Info is ------ " + application;
    }

}
