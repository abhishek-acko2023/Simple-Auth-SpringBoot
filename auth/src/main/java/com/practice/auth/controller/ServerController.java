package com.practice.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Auth")
public class ServerController {

    @GetMapping("")
    public String serverRunning(){
        return "Server Running!";
    }

}
