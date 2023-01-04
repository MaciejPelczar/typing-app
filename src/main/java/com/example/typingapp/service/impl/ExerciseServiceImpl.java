package com.example.typingapp.service.impl;

import com.example.typingapp.entity.Exercise;
import com.example.typingapp.repository.ExerciseRepository;
import com.example.typingapp.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Optional<Exercise> findByNumberAndLevel_Id(Integer nr, Long id) {
        return exerciseRepository.findByNumberAndLevel_Id(nr, id);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }


}
