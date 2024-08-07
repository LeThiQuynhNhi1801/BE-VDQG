package com.example.football.controller;

import com.example.football.dto.Request.RefereeRequest;
import com.example.football.service.RefereeService;
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
public class RefereeController {
    private final RefereeService refereeService;

    @PostMapping("/addreferee")
    public ResponseEntity<?> addReferee(@RequestBody RefereeRequest refereeRequest){
        return new ResponseEntity<>(refereeService.addReferee(refereeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/allreferee")
    public ResponseEntity<?> getAllReferee (){
        return new ResponseEntity<>(refereeService.getAllReferees(),HttpStatus.OK);
    }

    @GetMapping("/allrefereeON")
    public ResponseEntity<?> getAllRefereeON (){
        return new ResponseEntity<>(refereeService.getAllRefereesON(),HttpStatus.OK);
    }
}
