package com.example.football.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idTeam;

    private String name;

    @OneToOne
    private Stadium stadium;

    private String logo;

    private String phoneManager;

    private String emailManager;
}
