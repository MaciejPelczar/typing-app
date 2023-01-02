package com.example.typingapp.service;

import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
