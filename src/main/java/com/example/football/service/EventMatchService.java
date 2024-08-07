package com.example.football.service;

import com.example.football.dto.Request.EventRequest;
import com.example.football.dto.Response.GoalResponse;
import com.example.football.dto.Response.EMResponse;
import com.example.football.dto.Response.MatchRespone;
import com.example.football.dto.Response.RankingResponse;
import com.example.football.dto.Response.ematchResponse;

import java.util.List;

public interface EventMatchService {
    public List<EMResponse> getListEvent(Integer idMatch);
    public List<RankingResponse> getBXHFairplay(Integer idLeague);
    public List<ematchResponse> getBXHTeam1(Integer idLeague);

    public List<MatchRespone> getHistoryTeam(Integer idTeam, Integer idLeague);

    void addEvent(EventRequest eventRequest);

    List<GoalResponse> BXHGoals();

    // Other method declarations as needed
}

