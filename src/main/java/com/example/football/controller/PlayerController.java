package com.example.football.controller;

import com.example.football.dto.Request.PlayerRequest;
import com.example.football.service.PlayerService;
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
public class PlayerController {
    private final PlayerService playerService;
    @GetMapping("/playerListByTeamID/{idTeam}")
    public ResponseEntity<?> getPlayerListByTeamID(@PathVariable Integer idTeam) {
        return new ResponseEntity<>(playerService.playerListByTeamId(idTeam), HttpStatus.OK);
    }

    @GetMapping("/playernotinmainlineup/{idTeam}")
    public ResponseEntity<?> getPlayerListByTeamID(@PathVariable Integer idTeam,@RequestParam Integer idMatch) {
        return new ResponseEntity<>(playerService.playerListNotInmainlineup(idTeam,idMatch), HttpStatus.OK);
    }

    @PostMapping("/addplayer/{idTeam}")
    public ResponseEntity<?> addPlayer(@RequestBody PlayerRequest playerRequest,@PathVariable Integer idTeam){
        return new ResponseEntity<>(playerService.addPlayer(playerRequest,idTeam),HttpStatus.CREATED);
    }
}
