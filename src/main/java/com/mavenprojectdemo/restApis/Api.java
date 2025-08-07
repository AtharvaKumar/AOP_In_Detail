package com.mavenprojectdemo.restApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mavenprojectdemo.service.UserService;

@RestController
public class Api {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userLogin() {
        // Call the method in UserService
        userService.logIn();
        return "User login endpoint called successfully!";
    }
}
