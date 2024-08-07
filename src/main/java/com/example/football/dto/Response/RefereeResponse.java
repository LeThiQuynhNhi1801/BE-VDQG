package com.example.football.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefereeResponse {
    private String nameReferee;

    private String phone;

    private Date DOB;

    private String address;

    private String CCCD;
}
