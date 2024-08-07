package com.example.football.controller;

import com.example.football.dto.Request.LoginRequest;
import com.example.football.service.UserService;
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
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(userService.Login(loginRequest), HttpStatus.OK);
    }
}
