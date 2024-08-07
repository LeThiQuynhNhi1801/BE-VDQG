package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueRponse {
    private Integer idLeague;
    private String nameLeague;

    private Date startAt;

    private Date endAt;
}
