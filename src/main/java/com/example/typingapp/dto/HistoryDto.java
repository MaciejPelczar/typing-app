package com.example.typingapp.dto;

import com.example.typingapp.entity.Exercise;
import com.example.typingapp.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {

    private Long id;
    private LocalDateTime createdOn;
    private User userId;
    private Exercise exerciseId;
    private LocalTime lessonTime;
    private Integer fails;
}
