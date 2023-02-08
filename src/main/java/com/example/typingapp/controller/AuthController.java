package com.example.typingapp.controller;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.User;
import com.example.typingapp.service.HistoryService;
import com.example.typingapp.service.UserService;
import com.example.typingapp.util.ROLE;
import com.example.typingapp.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private HistoryService historyService;

    public AuthController(UserService userService, HistoryService historyService) {
        this.userService = userService;
        this.historyService = historyService;
    }

    //handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(user.getEmail()).orElse(null);
        if(existingUser != null){
            result.rejectValue("email", null,"There is already a user with same email!");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/register?success";
    }
    @GetMapping("/history")
    public String showHistory(Model model){
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userService.findByEmail(email).orElse(null);
        String name =  user.getName();
        model.addAttribute("name", name);

        String address = "adres";
        model.addAttribute("address", address);

        String role = SecurityUtils.getRole();
        List<HistoryDto> history = null;
        if(ROLE.ROLE_ADMIN.name().equals(role)){
            history = historyService.findAllHistories();
        }else{
            history = historyService.findHistoriesByUser();
        }
        model.addAttribute("history", history);
        return "history";
    }
}
