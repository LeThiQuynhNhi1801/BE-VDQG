package com.example.football.controller;

import com.example.football.dto.Request.EventRequest;
import com.example.football.service.EventMatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Api/V1")
@CrossOrigin("*")
public class EventMatchController {
    @Autowired
    private EventMatchService eventMatchService;

    @GetMapping("detailmatch/{idMatch}")
    public ResponseEntity<?> getEventMatch(@PathVariable("idMatch") Integer idMatch){
        return new ResponseEntity<>(eventMatchService.getListEvent(idMatch), HttpStatus.OK);
    }

    @GetMapping("bxhteam/{idLeague}")
    public ResponseEntity<?> getBXHTeam(@PathVariable Integer idLeague){
        return ResponseEntity.ok(eventMatchService.getBXHTeam1(idLeague));
    }
    @GetMapping("bxhfairplay/{idLeague}")
    public ResponseEntity<?> getBXHFairplay(@PathVariable Integer idLeague){
        return ResponseEntity.ok(eventMatchService.getBXHFairplay(idLeague));
    }

    @GetMapping("historyteam/{id}")
    public ResponseEntity<?> getHistoryTeam(@PathVariable Integer id,@RequestParam Integer idLeague){
        return ResponseEntity.ok(eventMatchService.getHistoryTeam(id,idLeague));
    }

    @PostMapping("/addevent")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest){
        eventMatchService.addEvent(eventRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/bxhgoals")
    public ResponseEntity<?> BXHGoals(){
        return new ResponseEntity<>(eventMatchService.BXHGoals(),HttpStatus.OK);
    }
}
