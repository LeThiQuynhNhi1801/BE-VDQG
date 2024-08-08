package com.example.football.service.Impl;

import com.example.football.dto.Request.LeagueRequest;
import com.example.football.dto.Request.ScheduleMatchRequest;
import com.example.football.dto.Response.GameResponse;
import com.example.football.dto.Response.GamesResponse;
import com.example.football.dto.Response.LeagueRponse;
import com.example.football.dto.Response.TeamResponse;
import com.example.football.entity.*;
import com.example.football.repo.*;
import com.example.football.service.LeagueService;
import com.example.football.untils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;
    private final TeamLeagueRepository teamLeagueRepository;
    private final TeamRepository teamRepository;
    private final RoundRepository roundRepository;
    private final GameRepository gameRepository;
    @Override
    public LeagueRponse addLeague(LeagueRequest leagueRequest,String dieule) {
//        leagueRepository.save(MapperUtils.toEntity(leagueRequest,League.class));
        League league = new League(null,leagueRequest.getNameLeague(),leagueRequest.getStartAt(),leagueRequest.getEndAt(),dieule);
        leagueRepository.save(league);
        for(int i=0;i<leagueRequest.getTeamRequestList().size();i++){
            TeamLeague teamLeague = new TeamLeague(null,teamRepository.getById(leagueRequest.getTeamRequestList().get(i).getIdTeam()),league);
            teamLeagueRepository.save(teamLeague);
        }
        return MapperUtils.toDTO(league,LeagueRponse.class);
    }

    @Override
    public List<GamesResponse> schedulematch(ScheduleMatchRequest scheduleMatchRequest) {
        League league = leagueRepository.getById(leagueRepository.currentLeague());
        List<GamesResponse> list = new ArrayList<>();
        int teams = scheduleMatchRequest.getTeamRequestList().size();
        int rounds = (teams -1)*2;
        int matches = teams/2;

        for (int round = 0; round < rounds; round++) {
            List<GameResponse> gameResponseList = new ArrayList<>();
            Round round1 = new Round(null,round+1,league);
            roundRepository.save(round1);
            for (int match = 0; match < matches; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);

                if (match == 0) {
                    away = teams - 1;
                }
                Game matchObj = new Game(null,teamRepository.getById(scheduleMatchRequest.getTeamRequestList().get(home).getIdTeam()),teamRepository.getById(scheduleMatchRequest.getTeamRequestList().get(away).getIdTeam()),null,round1);

                if (round >= rounds / 2) {
                    matchObj = new Game(null,teamRepository.getById(scheduleMatchRequest.getTeamRequestList().get(away).getIdTeam()),teamRepository.getById(scheduleMatchRequest.getTeamRequestList().get(home).getIdTeam()),null,round1);
                }

                gameRepository.save(matchObj);
                gameResponseList.add(MapperUtils.toDTO(matchObj,GameResponse.class));
            }
            GamesResponse gamesResponse = new GamesResponse(round1.getIdRound(),round+1,gameResponseList);
            list.add(gamesResponse);
        }
        return list;
    }

    @Override
    public List<GamesResponse> getschedulematch() {
        Integer idLeague = leagueRepository.currentLeague();
        List<Round> roundList = roundRepository.getRoundByLeague(idLeague);
        List<GamesResponse> list1 = new ArrayList<>();
        for (Round r:roundList){
            List<GameResponse> gameResponseList = new ArrayList<>();
            List<Game> list = gameRepository.getGameByRound(r.getIdRound());
            for (Game g:list){
                gameResponseList.add(MapperUtils.toDTO(g,GameResponse.class));
            }
            GamesResponse gamesResponse = new GamesResponse(r.getIdRound(),r.getNameRound(),gameResponseList);
            list1.add(gamesResponse);
        }
        return list1;
    }

    @Override
    public List<TeamResponse> teamleague(Integer idLeague) {
        List<Team> teamList = teamLeagueRepository.teamleague(idLeague);
        List<TeamResponse> teamResponseList = new ArrayList<>();
        for(Team team:teamList){
            TeamResponse teamResponse = new TeamResponse(team.getIdTeam(),team.getName(),team.getStadium(),team.getLogo());
            teamResponseList.add(teamResponse);
        }
        return teamResponseList;
    }

    @Override
    public List<TeamResponse> teamleague() {
        Integer idLeague = leagueRepository.currentLeague();
        List<Team> teamList = teamLeagueRepository.teamleague(idLeague);
        List<TeamResponse> teamResponseList = new ArrayList<>();
        for(Team team:teamList){
            TeamResponse teamResponse = new TeamResponse(team.getIdTeam(),team.getName(),team.getStadium(),team.getLogo());
            teamResponseList.add(teamResponse);
        }
        return teamResponseList;
    }

    @Override
    public List<LeagueRponse> getLeagues() {
        List<League> leagues = leagueRepository.findAll();
        return MapperUtils.toDTOs(leagues,LeagueRponse.class);
    }
}
