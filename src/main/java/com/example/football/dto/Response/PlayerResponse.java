package com.example.football.dto.Response;

import com.example.football.entity.Player;
import com.example.football.entity.Team;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
    private Integer id;

    private String name;

    private LocalDate dob;

    private Integer number;

    private String position;
    private Integer team;
    public PlayerResponse(Player player)
    {
        this.id = player.getId();
        this.name = player.getName();
        this.dob = player.getDob();
        this.number = player.getNumber();
        this.position = player.getPosition();
        this.team=player.getTeam().getIdTeam();
    }

}
