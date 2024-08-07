package com.example.football.service;
import com.example.football.dto.Request.AddTeamRequest;
import com.example.football.dto.Response.TeamResponse;
import com.example.football.entity.League;
import com.example.football.entity.Team;

import java.util.List;

public interface TeamService {
    TeamResponse addTeam(AddTeamRequest addTeamRequest);

    List<TeamResponse> allTeam();
}

