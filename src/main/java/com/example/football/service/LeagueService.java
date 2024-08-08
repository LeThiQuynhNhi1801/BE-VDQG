package com.example.football.service;

import com.example.football.dto.Request.LeagueRequest;
import com.example.football.dto.Request.ScheduleMatchRequest;
import com.example.football.dto.Response.GamesResponse;
import com.example.football.dto.Response.LeagueRponse;
import com.example.football.dto.Response.TeamResponse;
import com.example.football.entity.Team;

import java.util.List;

public interface LeagueService {
    LeagueRponse addLeague(LeagueRequest leagueRequest,String dieule);
    List<GamesResponse> schedulematch(ScheduleMatchRequest scheduleMatchRequest);

    List<GamesResponse> getschedulematch();

    List<TeamResponse> teamleague(Integer idLeague);

    List<TeamResponse> teamleague();

    List<LeagueRponse> getLeagues();

}
