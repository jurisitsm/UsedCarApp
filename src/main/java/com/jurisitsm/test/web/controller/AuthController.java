package com.jurisitsm.test.web.controller;

import com.jurisitsm.test.exception.UsedCarAdException;
import com.jurisitsm.test.service.UserService;
import com.jurisitsm.test.web.dto.request.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody UserRequest userRequest) throws UsedCarAdException {
        if (userService.existsByEmail(userRequest.getEmail())) {
            throw new UsedCarAdException("User with email: " + userRequest.getEmail() + " already exists.",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public void login(){

    }

    @PostMapping("/logout")
    public void logout(){

    }

}
