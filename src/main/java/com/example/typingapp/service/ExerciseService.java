package com.example.typingapp.service;

import com.example.typingapp.entity.Exercise;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExerciseService {

    Optional<Exercise> findByNumberAndLevel_Id(Integer nr, Long id);

    List<Exercise> findAll();

}
