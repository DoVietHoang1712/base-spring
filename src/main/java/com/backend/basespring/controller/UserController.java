package com.backend.basespring.controller;

import com.backend.basespring.model.User;
import com.backend.basespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String test() {
        return "123";
    }
    @PostMapping("")
    public String createUser(@RequestBody User body) {
        return null;
    }
}
