package com.example.football.repo;

import com.example.football.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("select p from Player p where p.team.idTeam=:idTeam")
    List<Player> findByTeam(@Param("idTeam") Integer idTeam);
}
