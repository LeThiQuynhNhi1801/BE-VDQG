package com.example.football.controller;

import com.example.football.dto.Request.CreateLineUpRequest;
import com.example.football.dto.Request.ListPlayerRequest;
import com.example.football.service.LineupService;
import com.example.football.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Api/V1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LineupController {
    private final LineupService lineupService;
    @PostMapping("/MainLineUp/{idMatch}")
    public ResponseEntity<?> addmainlineup(@PathVariable Integer idMatch, @RequestBody ListPlayerRequest listUser) {
        return new ResponseEntity<>(lineupService.createLineup(idMatch,listUser), HttpStatus.CREATED);
    }

    @PostMapping("/ReserveLineUp/{idMatch}")
    public ResponseEntity<?> addreservelineup(@PathVariable Integer idMatch, @RequestBody ListPlayerRequest listUser) {
        return new ResponseEntity<>(lineupService.createReserveLineup(idMatch,listUser), HttpStatus.CREATED);
    }

    @GetMapping("/lineup/{idTeam}")
    private ResponseEntity<?> getLineup(@RequestParam("idMatch") Integer idMatch,@PathVariable("idTeam") Integer idTeam){
        return new ResponseEntity<>(lineupService.getLineup(idMatch,idTeam),HttpStatus.OK);
    }

    @DeleteMapping("/lineup/{idTeam}")
    public ResponseEntity<?> deleteLineup(@PathVariable("idTeam") Integer idTeam,@RequestParam("idMatch") Integer idMatch){
        lineupService.deleteLineup(idTeam,idMatch);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

