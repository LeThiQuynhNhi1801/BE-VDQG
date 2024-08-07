package com.example.football.dto.Request;

import com.example.football.entity.Stadium;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class TeamRequest {
    private Integer idTeam;

    private String name;

//    private Stadium stadium;
}
