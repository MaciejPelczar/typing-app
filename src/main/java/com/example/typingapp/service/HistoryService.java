package com.example.typingapp.service;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.entity.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

    Optional<History> findByUserId(Long id);

    void createHistory(HistoryDto historyDto);

    List<HistoryDto> findHistoriesByUser();

    List<HistoryDto> findAllHistories();
}
