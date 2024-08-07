package com.example.football.service.Impl;

import com.example.football.dto.Request.CreateLineUpRequest;
import com.example.football.dto.Request.ListPlayerRequest;
import com.example.football.dto.Response.LineupResponse;
import com.example.football.entity.Game;
import com.example.football.entity.Lineup;
import com.example.football.entity.Player;
import com.example.football.entity.Team;
import com.example.football.repo.GameRepository;
import com.example.football.repo.LineupRepository;
import com.example.football.repo.PlayerRepository;
import com.example.football.repo.TeamRepository;
import com.example.football.service.LineupService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineupServiceImpl implements LineupService {
    private final LineupRepository lineupRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Override
    public List<LineupResponse> createLineup(Integer idGame, ListPlayerRequest listUser) {
        Game game = gameRepository.findById(idGame).get();
        List<Lineup> lineupListNew = new ArrayList<>();
        List<LineupResponse> createLineUpRequestNew = new ArrayList<>();
        for (int i = 0; i < listUser.getListUser().size(); i++) {
            Player player = playerRepository.getById(listUser.getListUser().get(i));
            Lineup lineup = new Lineup();
            lineup.setGame(game);
            lineup.setPlayer(player);
            lineup.setIdTeam(player.getTeam().getIdTeam());
            lineup.setStatus("main");
            lineupListNew.add(lineup);
            createLineUpRequestNew.add(new LineupResponse(lineup));
        }
        lineupRepository.saveAll(lineupListNew);
        return createLineUpRequestNew;
    }

    @Override
    public List<LineupResponse> createReserveLineup(Integer idGame, ListPlayerRequest listUser) {
        Game game = gameRepository.findById(idGame).get();
        List<Lineup> lineupListNew = new ArrayList<>();
        List<LineupResponse> createLineUpRequestNew = new ArrayList<>();
        for (int i = 0; i < listUser.getListUser().size(); i++) {
            Player player = playerRepository.getById(listUser.getListUser().get(i));
            Lineup lineup = new Lineup();
            lineup.setGame(game);
            lineup.setPlayer(player);
            lineup.setIdTeam(player.getTeam().getIdTeam());
            lineup.setStatus("reserve");
            lineupListNew.add(lineup);
            createLineUpRequestNew.add(new LineupResponse(lineup));
        }
        lineupRepository.saveAll(lineupListNew);
        return createLineUpRequestNew;
    }

    @Override
    public List<LineupResponse> getLineup(Integer idMatch, Integer idTeam) {
        Game game = gameRepository.findById(idMatch).get();
        List<Lineup> lineupList = new ArrayList<>();
        lineupList = lineupRepository.getLineup(idMatch,idTeam);
        List<LineupResponse> lineupResponses = new ArrayList<>();
        for(Lineup lineup : lineupList){
            LineupResponse lineupResponse = new LineupResponse(lineup);
            lineupResponses.add(lineupResponse);
        }
        return lineupResponses;
    }

    @Override
    public void deleteLineup(Integer idTeam, Integer idMatch) {
        List<Lineup> lineups = lineupRepository.getLineup(idMatch,idTeam);
        for(Lineup lineup:lineups){


            lineupRepository.delete(lineup);
        }
    }
}

