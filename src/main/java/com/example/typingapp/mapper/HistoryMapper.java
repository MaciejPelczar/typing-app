package com.example.typingapp.mapper;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.entity.History;

public class HistoryMapper {

    //map Exercise entity to ExerciseDto
    public static HistoryDto mapToHistoryDto(History history) {
        return HistoryDto.builder()
                .id(history.getId())
                .createdOn(history.getCreatedOn())
                .userId(history.getUserId())
                .exerciseId(history.getExerciseId())
                .lessonTime(history.getLessonTime())
                .fails(history.getFails())
                .build();
    }

    //map HistoryDto to History entity
    public static History mapToHistory(HistoryDto historyDto){
        return History.builder()
                .id(historyDto.getId())
                .createdOn(historyDto.getCreatedOn())
                .userId(historyDto.getUserId())
                .exerciseId(historyDto.getExerciseId())
                .lessonTime(historyDto.getLessonTime())
                .fails(historyDto.getFails())
                .build();
    }

}
