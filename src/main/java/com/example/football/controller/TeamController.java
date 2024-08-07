package com.example.football.controller;

import com.example.football.dto.Request.AddTeamRequest;
import com.example.football.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Api/V1")
@CrossOrigin("*")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("addteam")
    public ResponseEntity<?> addTeam(@RequestBody AddTeamRequest addTeamRequest){
        return new ResponseEntity<>(teamService.addTeam(addTeamRequest), HttpStatus.CREATED);
    }

    @GetMapping("allteam")
    public ResponseEntity<?> allTeam(){
        return new ResponseEntity<>(teamService.allTeam(),HttpStatus.OK);
    }
}
