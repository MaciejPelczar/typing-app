package com.example.typingapp.repository;

import com.example.typingapp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByNumberAndLevel_Id(Integer nr, Integer id);
}
