package com.example.football.repo;

import com.example.football.entity.Team;
import com.example.football.entity.TeamLeague;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamLeagueRepository extends JpaRepository<TeamLeague,Integer> {
    @Query("select tl.team from TeamLeague tl where tl.league.idLeague=:idLeague")
    List<Team> teamleague(@Param("idLeague") Integer idLeague);
}
