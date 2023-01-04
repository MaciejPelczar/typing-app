package com.example.typingapp.controller;


import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.Exercise;
import com.example.typingapp.service.ExerciseService;
import com.example.typingapp.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class TypingAppController {

    private static final Logger logger = LoggerFactory.getLogger(TypingAppController.class);

        private ExerciseService exerciseService;
        private LevelService levelService;

    public TypingAppController(ExerciseService exerciseService, LevelService levelService) {
        this.exerciseService = exerciseService;
        this.levelService = levelService;
    }

    // handler method to handle http://localhost:8080/
        @GetMapping("/")
        public String viewIndex(){
            return "index";
        }

        @GetMapping("/menu")
        public String showMenu(Model model){
            model.addAttribute("listLevel", levelService.findAll());
            model.addAttribute("exercise", new Exercise());
            return "menu";
        }

        @PostMapping("/menu/start")
        public String startTest(@ModelAttribute("exercise") Exercise exercise, Model model){
            int number = exercise.getNumber();
            Long levelId = exercise.getLevel().getId();

            String parameters = "number="+number+"&id="+levelId;


            logger.warn("Number : " + number +"   Id: "+ levelId);
            model.addAttribute("exercise", exerciseService.findByNumberAndLevel_Id(number, levelId));
            return "redirect:/lesson?"+parameters;
        }
}
