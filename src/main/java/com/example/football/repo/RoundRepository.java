package com.example.football.repo;

import com.example.football.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round,Integer> {
    @Query("select r from Round r where r.league.idLeague=:idLeague")
    List<Round> getRoundByLeague(@Param("idLeague") Integer idLeague);
}
