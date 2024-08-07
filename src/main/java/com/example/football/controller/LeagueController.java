package com.example.football.controller;

import com.example.football.dto.Request.ScheduleMatchRequest;
import com.example.football.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Api/V1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LeagueController {
    private final LeagueService leagueService;

    @GetMapping("/teamleague/{idLeague}")
    public ResponseEntity<?> teamleague(@PathVariable Integer idLeague){
        return new ResponseEntity<>(leagueService.teamleague(idLeague), HttpStatus.OK);
    }

    @GetMapping("/teamleague")
    public ResponseEntity<?> teamleague(){
        return new ResponseEntity<>(leagueService.teamleague(), HttpStatus.OK);
    }

    @GetMapping("/allleague")
    public ResponseEntity<?> allleague(){
        return new ResponseEntity<>(leagueService.getLeagues(),HttpStatus.OK);
    }

    @PostMapping("chooseopenteam")
    public ResponseEntity<?> schedulematch(@RequestBody ScheduleMatchRequest scheduleMatchRequest){
        return new ResponseEntity<>(leagueService.schedulematch(scheduleMatchRequest),HttpStatus.CREATED);
    }

    @GetMapping("/schedulematch")
    public ResponseEntity<?> schedulematch(){
        return new ResponseEntity<>(leagueService.getschedulematch(),HttpStatus.OK);
    }
}
