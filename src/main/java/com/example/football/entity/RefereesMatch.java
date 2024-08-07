package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefereesMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idRefereesMatch;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Referees referees;
}
