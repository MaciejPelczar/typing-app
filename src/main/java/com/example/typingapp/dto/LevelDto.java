package com.example.typingapp.dto;

import com.example.typingapp.entity.Exercise;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LevelDto {

    private Long id;
    private String name;
    private Set<Exercise> exercise;
}
