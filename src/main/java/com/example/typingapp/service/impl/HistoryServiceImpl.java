package com.example.typingapp.service.impl;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.entity.History;
import com.example.typingapp.entity.User;
import com.example.typingapp.mapper.HistoryMapper;
import com.example.typingapp.repository.HistoryRepository;
import com.example.typingapp.repository.UserRepository;
import com.example.typingapp.service.HistoryService;
import com.example.typingapp.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    private HistoryRepository historyRepository;
    private UserRepository userRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<History> findByUserId(Long id) {
        return historyRepository.findByUserId(id);
    }

    @Override
    public void createHistory(HistoryDto historyDto){
        String email = SecurityUtils.getCurrentUser().getUsername();
        historyDto.setUserId(userRepository.findByEmail(email).orElse(null));
        History history = HistoryMapper.mapToHistory(historyDto);
        historyRepository.save(history);
    }

    @Override
    public List<HistoryDto> findHistoriesByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email).orElse(null);
        Long userId = user.getId();
        List<History> history = historyRepository.findHistoriesByUser(userId);
        return history.stream()
                .map((hist) -> HistoryMapper.mapToHistoryDto(hist))
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDto> findAllHistories() {
        List<History> history = historyRepository.findAll();
        return history.stream().map(HistoryMapper::mapToHistoryDto).collect(Collectors.toList());
    }
}
