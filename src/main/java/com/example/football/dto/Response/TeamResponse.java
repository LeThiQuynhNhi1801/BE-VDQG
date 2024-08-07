package com.example.football.dto.Response;

import com.example.football.entity.Stadium;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private Integer idTeam;

    private String name;

    private Stadium stadium;

    private String logo;

}
