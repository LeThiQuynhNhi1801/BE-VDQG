package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Integer idUser;
    private Integer idTeam;
    private String token;
    private String role;
}
