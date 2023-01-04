package com.example.typingapp.service.impl;

import com.example.typingapp.entity.Level;
import com.example.typingapp.repository.LevelRepository;
import com.example.typingapp.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }
}
