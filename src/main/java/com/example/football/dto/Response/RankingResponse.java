package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankingResponse {
    private String nameTeam;
    private Integer idteam;
    private Integer thevang;
    private Integer thedo;

    public int getTotalPenaltyPoints() {
        return thevang + thedo * 3;
    }
}
