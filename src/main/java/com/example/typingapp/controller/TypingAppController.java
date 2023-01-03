package com.example.typingapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TypingAppController {

        // handler method to handle http://localhost:8080/
        @GetMapping("/index")
        public String viewBlogPosts(){
            return "index";
        }


}
