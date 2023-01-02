package com.example.typingapp.service.impl;

import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.Role;
import com.example.typingapp.entity.User;
import com.example.typingapp.repository.RoleRepository;
import com.example.typingapp.repository.UserRepository;
import com.example.typingapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() +" "+ registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());

        // use spring security to encrypte password
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
