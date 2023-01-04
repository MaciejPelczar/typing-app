package com.example.typingapp.controller;

import com.example.typingapp.dto.RegistrationDto;
import com.example.typingapp.entity.Exercise;
import com.example.typingapp.entity.User;
import com.example.typingapp.service.ExerciseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private static final Logger logger = LoggerFactory.getLogger(TypingAppController.class);

    private ExerciseService exerciseService;

    Exercise exercise = new Exercise();

    public LessonController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public String showExe(Model model, @RequestParam int number, @RequestParam long id){
        exercise = exerciseService.findByNumberAndLevel_Id(number, id).orElse(null);
        model.addAttribute("exercise", exercise);
        return "lesson";
    }

    @GetMapping(params = "back")
    public String backToMenu(){
        return "redirect:/menu?stop";
    }

    @GetMapping(params = "stop")
    public String stopWriting(Model model){
        model.addAttribute("exercise", exercise);
        return "lesson";
    }

    @PostMapping(params = {"stopp","time","levelName","lessonNumb","failsNumber"})
    public String lessonSuccess(Model model, @RequestParam String time, @RequestParam String lessonNumb,
                                @RequestParam String levelName, @RequestParam String failsNumber){
        String timePractise = "&time=" + time;
        String lNumb = "&lessonNumb=" + lessonNumb;
        String lName = "&levelName=" + levelName;
        String fNumb = "&failsNumber=" + failsNumber;
        model.addAttribute("exercise", exercise);
        return "redirect:/menu?success"+timePractise+lNumb+lName+fNumb;
    }

}
