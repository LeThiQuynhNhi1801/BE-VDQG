package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchRespone {
    private Integer idMatch;
    private String nameHomeTeam;
    private String nameAwayTeam;
    private Integer scoreHomeTeam;
    private Integer scoreAwayTeam;
}
