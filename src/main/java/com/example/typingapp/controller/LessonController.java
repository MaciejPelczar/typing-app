package com.example.typingapp.controller;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.entity.Exercise;
import com.example.typingapp.entity.User;
import com.example.typingapp.service.ExerciseService;
import com.example.typingapp.service.HistoryService;
import com.example.typingapp.service.UserService;
import com.example.typingapp.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private static final Logger logger = LoggerFactory.getLogger(TypingAppController.class);

    private ExerciseService exerciseService;
    private UserService userService;
    private HistoryService historyService;
    Exercise exercise = new Exercise();

    public LessonController(ExerciseService exerciseService, UserService userService, HistoryService historyService) {
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping
    public String showExe(Model model, @RequestParam int number, @RequestParam long id){
        exercise = exerciseService.findByNumberAndLevel_Id(number, id).orElse(null);
        model.addAttribute("exercise", exercise);
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userService.findByEmail(email).orElse(null);
        String name =  user.getName();
        String role = SecurityUtils.getRole();
        model.addAttribute("name", name);
        model.addAttribute("role", role);
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

        HistoryDto historyDto = new HistoryDto();

        String[] timeArr = time.split(":");
        LocalTime myTimeObj = LocalTime.of(0,Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]));
        historyDto.setLessonTime(myTimeObj);

        historyDto.setExerciseId(exercise);

        historyDto.setFails(Integer.valueOf(failsNumber));

        historyService.createHistory(historyDto);

        return "redirect:/menu?success"+timePractise+lNumb+lName+fNumb;
    }

}
