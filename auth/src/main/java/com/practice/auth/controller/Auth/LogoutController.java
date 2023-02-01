package com.practice.auth.controller.Auth;

import com.practice.auth.service.Auth.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Auth")
public class LogoutController {

    private static LogoutService logoutService;

    @Autowired
    public LogoutController(LogoutService logoutService) {
        LogoutController.logoutService = logoutService;
    }

    @DeleteMapping("/logout")
    public Object logout(@RequestHeader ("Authorization") String token){
       return logoutService.logout(token);
    }

}
