package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalResponse {
    private String namePlayer;
    private String nameTeam;

    private Integer goals;
}
