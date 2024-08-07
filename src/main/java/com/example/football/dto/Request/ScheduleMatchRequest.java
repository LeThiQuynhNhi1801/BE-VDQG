package com.example.football.dto.Request;

import com.example.football.dto.Response.EMResponse;
import lombok.Data;

import java.util.List;
@Data
public class ScheduleMatchRequest {
    List<TeamRequest> teamRequestList;
}
