package com.example.football.dto.Response;

import com.example.football.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesResponse {
    private Integer idRound;
    private Integer nameRound;
    List<GameResponse> gameResponseList;
}
