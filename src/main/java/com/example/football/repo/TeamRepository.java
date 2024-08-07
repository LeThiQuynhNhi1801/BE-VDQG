package com.example.football.repo;
import com.example.football.entity.Player;
import com.example.football.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
//    @Query("select p from Team p ")
//    List<Team> findAllTeam();

    @Query("select t from TeamLeague t where t.league.idLeague =:idLeague")
    List<Team> findAllTeam(@Param("idLeague") Integer idLeague);
}