package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idMatch;

    @ManyToOne
    @JoinColumn(name = "hometeam_id" ,referencedColumnName = "idTeam")
    private Team idHometeam;
    @ManyToOne
    @JoinColumn(name = "awayteam_id" ,referencedColumnName = "idTeam")
    private Team idAwayteam;

    private LocalDateTime timeMatch;

    @ManyToOne
    private Round round;

}
