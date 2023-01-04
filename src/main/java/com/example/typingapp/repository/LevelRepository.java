package com.example.typingapp.repository;

import com.example.typingapp.entity.Exercise;
import com.example.typingapp.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findAll();
}
