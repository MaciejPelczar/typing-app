package com.example.typingapp.mapper;

import com.example.typingapp.dto.ExerciseDto;
import com.example.typingapp.entity.Exercise;
public class ExerciseMapper {

    //map Exercise entity to ExerciseDto
    public static ExerciseDto mapToExerciseDto(Exercise exercise){
        return ExerciseDto.builder()
                .id(exercise.getId())
                .text(exercise.getText())
                .number(exercise.getNumber())
                .level(exercise.getLevel())
                .build();
    }

    //map ExerciseDto to Exercise entity
    public static Exercise mapToExercise(ExerciseDto exerciseDto){
        return Exercise.builder()
                .id(exerciseDto.getId())
                .text(exerciseDto.getText())
                .number(exerciseDto.getNumber())
                .level(exerciseDto.getLevel())
                .build();
    }
}
