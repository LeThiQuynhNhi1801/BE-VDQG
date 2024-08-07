package com.example.football.dto.Request;

import com.example.football.entity.Team;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PlayerRequest {
    private String name;

    private LocalDate dob;

    private Integer number;

    private String position;

    private String cccd;

    private String quoctich;

    private String hometown;
}
