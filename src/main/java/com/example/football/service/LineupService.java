package com.example.football.service;
import com.example.football.dto.Request.CreateLineUpRequest;
import com.example.football.dto.Request.ListPlayerRequest;
import com.example.football.dto.Response.LineupResponse;
import com.example.football.entity.Lineup;

import java.util.List;

public interface LineupService {
  List<LineupResponse> createLineup(Integer idGame, ListPlayerRequest listUser);

  List<LineupResponse> createReserveLineup(Integer idGame, ListPlayerRequest listUser);

  List<LineupResponse> getLineup(Integer idMatch, Integer idTeam);

  public void deleteLineup(Integer idTeam,Integer idMatch);

//  List<LineupResponse> createReserveLineup(Integer idGame, CreateLineUpRequest createLineUpRequest);
}
