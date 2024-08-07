package com.example.football.service;

import com.example.football.dto.Request.RefereeRequest;
import com.example.football.dto.Response.PlayerResponse;
import com.example.football.dto.Response.RefereeResponse;

import java.util.List;

public interface RefereeService {
    RefereeResponse addReferee(RefereeRequest refereeRequest);

    List<RefereeResponse> getAllRefereesON();

    List<RefereeResponse> getAllReferees();
}
