package com.example.typingapp.service;

import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.User;

import java.util.Optional;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    Optional<User> findByEmail(String email);
}
