package com.practice.auth.controller.User;

import com.practice.auth.dto.UserResponseDTO;
import com.practice.auth.model.User.User;
import com.practice.auth.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("User")
public class UserController {

    private static UserService userService;
    @Autowired
    public UserController(UserService userService) {
        UserController.userService = userService;
    }

    @GetMapping("/getUserDetails")
    public Object getUserDetails(@RequestHeader("Authorization") String token){
        return userService.getUserDetails(token);
    }
}
