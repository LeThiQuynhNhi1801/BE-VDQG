package com.example.football.service;
import com.example.football.dto.Request.PlayerRequest;
import com.example.football.dto.Response.PlayerResponse;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> playerListByTeamId(Integer idTeam);

    List<PlayerResponse> playerListNotInmainlineup(Integer idTeam,Integer idMatch);

    PlayerResponse addPlayer(PlayerRequest playerRequest, Integer idTeam);
}
