package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {
    private Integer idMatch;

    private TeamResponse hometeam;

    private TeamResponse awayteam;

    private LocalDateTime timeMatch;
}
