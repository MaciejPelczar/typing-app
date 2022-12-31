package com.example.typingapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(nullable = false, columnDefinition = "longtext")
    private String text;
    @Column(nullable = false)
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
