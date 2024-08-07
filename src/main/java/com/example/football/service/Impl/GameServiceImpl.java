package com.example.football.service.Impl;

import com.example.football.dto.Response.GameResponse;
import com.example.football.dto.Response.GamesResponse;
import com.example.football.dto.Response.PlayerResponse;
import com.example.football.dto.Response.TeamResponse;
import com.example.football.entity.Game;
import com.example.football.repo.GameRepository;
import com.example.football.repo.LeagueRepository;
import com.example.football.service.GameService;
import com.example.football.untils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final LeagueRepository leagueRepository;
    @Override
    public List<GameResponse> getGameComing(Integer idTeam) {
        Integer idLeague = leagueRepository.currentLeague();
        List<Game> games = gameRepository.getGameComing(idTeam,idLeague);
        List<GameResponse> gameResponseList = new ArrayList<>();
        for(Game game:games){
            TeamResponse hometeam = new TeamResponse(game.getIdHometeam().getIdTeam(),game.getIdHometeam().getName(),game.getIdHometeam().getStadium(),game.getIdHometeam().getLogo());
            TeamResponse awayteam = new TeamResponse(game.getIdAwayteam().getIdTeam(),game.getIdAwayteam().getName(),game.getIdAwayteam().getStadium(),game.getIdAwayteam().getLogo());
            GameResponse gameResponse = new GameResponse(game.getIdMatch(),hometeam,awayteam,game.getTimeMatch());
            gameResponseList.add(gameResponse);
        }
        return gameResponseList;
    }
}

