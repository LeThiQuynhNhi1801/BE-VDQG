package com.example.football.repo;

import com.example.football.dto.Response.PlayerResponse;
import com.example.football.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("select g from Game g where (g.idHometeam.idTeam=:idTeam or g.idAwayteam.idTeam=:idTeam) and g.round.league.idLeague=:idLeague order by g.idMatch ")
    List<Game> getGameComing(@Param("idTeam") Integer idTeam,@Param("idLeague") Integer idLeague);

    @Query("select g from Game g where g.round.idRound=:idRound")
    List<Game> getGameByRound(@Param("idRound") Integer idRound);

}
