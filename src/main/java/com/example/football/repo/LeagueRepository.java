package com.example.football.repo;

import com.example.football.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeagueRepository extends JpaRepository<League,Integer> {
    @Query("select l.idLeague from League l order by (-l.idLeague) limit 1")
    Integer currentLeague();
}
