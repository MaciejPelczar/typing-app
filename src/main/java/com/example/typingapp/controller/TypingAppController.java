package com.example.typingapp.controller;


import com.example.typingapp.entity.Exercise;
import com.example.typingapp.entity.User;
import com.example.typingapp.service.ExerciseService;
import com.example.typingapp.service.LevelService;
import com.example.typingapp.service.UserService;
import com.example.typingapp.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypingAppController {

    private static final Logger logger = LoggerFactory.getLogger(TypingAppController.class);
        private ExerciseService exerciseService;
        private LevelService levelService;
        private UserService userService;

    public TypingAppController(ExerciseService exerciseService, LevelService levelService, UserService userService) {
        this.exerciseService = exerciseService;
        this.levelService = levelService;
        this.userService = userService;
    }

        @GetMapping("/")
        public String viewIndex(Model model) {
            return "index";
        }

        @GetMapping("/menu")
        public String showMenu(Model model){
            model.addAttribute("listLevel", levelService.findAll());
            model.addAttribute("exercise", new Exercise());
            String email = SecurityUtils.getCurrentUser().getUsername();
            User user = userService.findByEmail(email).orElse(null);
            String name =  user.getName();
            model.addAttribute("name", name);
            return "menu";
        }

        @PostMapping("/menu/start")
        public String startTest(@ModelAttribute("exercise") Exercise exercise, Model model){
            int number = exercise.getNumber();
            Long levelId = exercise.getLevel().getId();
            String parameters = "number="+number+"&id="+levelId;
            model.addAttribute("exercise", exerciseService.findByNumberAndLevel_Id(number, levelId));
            return "redirect:/lesson?"+parameters;
        }
}
