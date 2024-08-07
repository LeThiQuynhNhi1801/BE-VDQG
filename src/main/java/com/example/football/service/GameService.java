package com.example.football.service;
import com.example.football.dto.Response.GameResponse;
import com.example.football.dto.Response.GamesResponse;
import com.example.football.dto.Response.PlayerResponse;
import com.example.football.entity.Game;

import java.util.List;

public interface GameService {
    List<GameResponse> getGameComing(Integer idTeam);
}
