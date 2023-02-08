package com.example.typingapp.repository;

import com.example.typingapp.dto.HistoryDto;
import com.example.typingapp.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findByUserId(Long id);

    @Query(value = "select * from history h where h.user_id =:userId", nativeQuery = true)
    List<History> findHistoriesByUser(Long userId);


}
