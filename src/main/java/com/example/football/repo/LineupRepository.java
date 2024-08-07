package com.example.football.repo;

import com.example.football.entity.Lineup;
import com.example.football.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineupRepository extends JpaRepository<Lineup, Integer> {
    // Add custom query methods if needed
    @Query("select l from  Lineup  l where l.game.idMatch =:idMatch and l.player.team.idTeam=:idTeam and l.status='main' ")
    List<Lineup> getMainLineup(@Param("idMatch") Integer idMatch,@Param("idTeam") Integer idTeam);

    @Query("select l from  Lineup  l where l.game.idMatch =:idMatch and l.player.team.idTeam=:idTeam ")
    List<Lineup> getLineup(@Param("idMatch") Integer idMatch,@Param("idTeam") Integer idTeam);

    @Query("select l.game.idMatch from Lineup l where l.game.round.league.idLeague=:idLeague and l.player.team.idTeam=:idTeam and l.game.timeMatch< now() group by l.game.idMatch")
    List<Integer> getMatch(@Param("idTeam") Integer idTeam,@Param(("idLeague")) Integer idLeague);
}
