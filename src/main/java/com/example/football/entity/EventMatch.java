package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idEM;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Lineup lineup;

    private String description;

    private Integer timeEvent;
}
