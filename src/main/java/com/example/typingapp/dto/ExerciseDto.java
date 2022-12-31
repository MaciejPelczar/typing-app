package com.example.typingapp.dto;

import com.example.typingapp.entity.Level;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseDto {

    private Long id;
    private String text;
    private Integer number;
    private Level level;
}
