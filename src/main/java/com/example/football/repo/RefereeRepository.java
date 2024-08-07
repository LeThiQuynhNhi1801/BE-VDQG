package com.example.football.repo;

import com.example.football.dto.Response.PlayerResponse;
import com.example.football.entity.Referees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefereeRepository extends JpaRepository<Referees,Integer> {
    @Query("select r from Referees r where r.status = 'on' ")
    List<Referees> getAll();
}
