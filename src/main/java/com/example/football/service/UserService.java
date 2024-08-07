package com.example.football.service;

import com.example.football.dto.Request.LoginRequest;
import com.example.football.dto.Response.LoginResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    LoginResponse Login(LoginRequest loginRequest);
}
