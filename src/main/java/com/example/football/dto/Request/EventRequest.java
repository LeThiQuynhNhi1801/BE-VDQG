package com.example.football.dto.Request;

import lombok.Data;

@Data
public class EventRequest {
    private Integer idEvent;

    private Integer idLineup;

    private String description;

    private Integer timeEvent;
}
