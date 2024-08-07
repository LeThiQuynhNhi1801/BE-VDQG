package com.example.football.repo;
import com.example.football.entity.EventMatch;
import com.example.football.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface EventMatchRepository extends JpaRepository<EventMatch, Integer> {

    @Query("select count (em.lineup.idLineup) from EventMatch em where em.lineup.idTeam=:idTeam and em.event.idEvent=4")
    Integer getyellowcard(@Param("idTeam") Integer idTeam);

    @Query("select count (em.lineup.idLineup) from EventMatch em where em.lineup.idTeam=:idTeam and em.event.idEvent=5")
    Integer getredcard(@Param("idTeam") Integer idTeam);

    @Query("select em from EventMatch em where em.lineup.idTeam=:idTeam and em.lineup.game.idMatch=:idMatch")
    List<EventMatch> getevent(@Param("idTeam") Integer idTeam,@Param("idMatch") Integer idMatch);

    @Query("select em from EventMatch em where em.lineup.game.idMatch=:idMatch")
    List<EventMatch> getDetail(@Param("idMatch") Integer idMatch);


}
