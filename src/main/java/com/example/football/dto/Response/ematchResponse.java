package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ematchResponse {
    private String nameTeam;
    private Integer idteam;
    private Integer win;
    private Integer loss;
    private Integer drawn;
    private Long hieuso;
}
