package com.example.football.dto.Response;

import com.example.football.entity.Game;
import com.example.football.entity.Lineup;
import com.example.football.entity.Player;
import com.example.football.service.LineupService;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class LineupResponse {
    private Integer idLineup;

    private Player player;
    private Integer game;
    private String status;
    public LineupResponse(Lineup lineup) {
        this.idLineup = lineup.getIdLineup();
        this.player = lineup.getPlayer();
        this.game=lineup.getGame().getIdMatch();
        this.status=lineup.getStatus();
    }
}
