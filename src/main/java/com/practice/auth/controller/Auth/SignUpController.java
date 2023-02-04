package com.practice.auth.controller.Auth;


import com.practice.auth.dto.SignUpResponseDTO;
import com.practice.auth.model.User.User;
import com.practice.auth.service.Auth.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Auth")
public class SignUpController {

    private static SignUpService signUpService;
    @Autowired
    public SignUpController(SignUpService signUpService) {
        SignUpController.signUpService = signUpService;
    }
    @PostMapping("/signUp")
    public SignUpResponseDTO addUser(@RequestBody User user){
        return signUpService.addUser(user);
    };
}
