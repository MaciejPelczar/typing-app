package com.example.typingapp.mapper;

import com.example.typingapp.dto.LevelDto;
import com.example.typingapp.entity.Level;

public class LevelMapper {

    //map Level entity to LevelDto
    public static LevelDto mapToLevelDto(Level level){
        return LevelDto.builder()
                .id(level.getId())
                .name(level.getName())
                .exercise(level.getExercise())
                .build();
    }

    //map LevelDto to Level entity
    public static Level mapToLevel(LevelDto levelDto){
        return Level.builder()
                .id(levelDto.getId())
                .name(levelDto.getName())
                .exercise(levelDto.getExercise())
                .build();
    }
}
