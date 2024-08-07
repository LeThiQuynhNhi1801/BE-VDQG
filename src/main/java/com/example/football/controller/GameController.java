package com.example.football.controller;

import com.example.football.service.GameService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("gamecoming/{idTeam}")
    public ResponseEntity<?> gamecoming(@PathVariable("idTeam") Integer idTeam){
        return new ResponseEntity<>(gameService.getGameComing(idTeam), HttpStatus.OK);
    }
}
