package com.example.football.service.Impl;
import com.example.football.dto.Request.PlayerRequest;
import com.example.football.dto.Response.PlayerResponse;
import com.example.football.entity.Lineup;
import com.example.football.entity.Player;
import com.example.football.entity.Team;
import com.example.football.repo.LineupRepository;
import com.example.football.repo.PlayerRepository;
import com.example.football.repo.TeamRepository;
import com.example.football.service.PlayerService;
import com.example.football.untils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final LineupRepository lineupRepository;

    private final TeamRepository teamRepository;
    @Override
    public List<PlayerResponse> playerListByTeamId(Integer idTeam)
    {
        List<PlayerResponse> playerResponseList = new ArrayList<>();
        List<Player> playerList = playerRepository.findByTeam(idTeam);
        for (Player player : playerList) {
            playerResponseList.add(new PlayerResponse(player));
        }
        return playerResponseList;
    }

    @Override
    public List<PlayerResponse> playerListNotInmainlineup(Integer idTeam, Integer idMatch) {
        List<Lineup> list  = lineupRepository.getMainLineup(idMatch,idTeam);
        List<Player> playerList = playerRepository.findByTeam(idTeam);
        List<PlayerResponse> playerListResponse = new ArrayList<>();
        for (Lineup lineup: list){
            for (Player player:playerList){
                if(lineup.getPlayer().equals(player)){
                    playerList.remove(player);
                    break;
                }
            }
        }
        for (Player player : playerList){
            PlayerResponse a = new PlayerResponse(player.getId(),player.getName(),player.getDob(),player.getNumber(),player.getPosition(),player.getTeam().getIdTeam());
            playerListResponse.add(a);
        }
        return playerListResponse;
    }

    @Override
    public PlayerResponse addPlayer(PlayerRequest playerRequest, Integer idTeam) {
        Team team = teamRepository.getById(idTeam);
        Player player = new Player(null,playerRequest.getName(),playerRequest.getDob(),playerRequest.getNumber(),playerRequest.getPosition(),team);
        playerRepository.save(player);
        return  MapperUtils.toDTO(player,PlayerResponse.class);
    }


}
