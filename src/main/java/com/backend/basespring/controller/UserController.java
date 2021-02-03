package com.backend.basespring.controller;

import com.backend.basespring.model.User;
import com.backend.basespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("test")
    public String test() {
        return "123";
    }
    @PostMapping("te")
    public String createUser(@RequestBody User body) {
        return null;
    }
}
