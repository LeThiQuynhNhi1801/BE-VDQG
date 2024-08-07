package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamLeague {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idTeamLeague;

    @ManyToOne
    private Team team;

    @ManyToOne
    private League league;
}
