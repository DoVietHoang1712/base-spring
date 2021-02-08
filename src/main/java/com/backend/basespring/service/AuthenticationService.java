package com.backend.basespring.service;

import com.backend.basespring.dto.LoginDto;
import com.backend.basespring.dto.UserDto;
import com.backend.basespring.model.User;
import com.backend.basespring.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        return user;
    }

    public User findById(String id) {
        Optional<User> optionalUser = userRepository.findById(new ObjectId(id));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
