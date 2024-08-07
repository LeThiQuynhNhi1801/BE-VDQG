package com.example.football.dto.Request;

import com.example.football.entity.Stadium;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class AddTeamRequest {

    private String name;

    private Stadium stadium;

    private String logo;

    private String phoneManager;

    private String emailManager;
}
