package com.example.football.entity;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lineup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idLineup;

    @ManyToOne
    private Player player;

    private Integer idTeam;

    @ManyToOne
    private Game game;

    private String status;
}
