package com.example.football.dto.Request;

import com.example.football.entity.League;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LeagueRequest {
    private String nameLeague;

    private Date startAt;

    private Date endAt;

    private List<TeamRequest> teamRequestList;
}
