package com.practice.auth.controller.Auth;


import com.practice.auth.dto.LoginResponseDTO;
import com.practice.auth.model.Auth.Login;
import com.practice.auth.service.Auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Auth")
public class LoginController {

    private static LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        LoginController.loginService = loginService;
    }

    @PostMapping("/Login")
    public LoginResponseDTO login(@RequestBody Login login){
        return loginService.login(login);
    }
}
