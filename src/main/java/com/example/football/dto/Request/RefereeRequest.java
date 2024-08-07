package com.example.football.dto.Request;

import lombok.Data;

import java.util.Date;
@Data
public class RefereeRequest {
    private String nameReferee;

    private String phone;

    private Date DOB;

    private String address;

    private String CCCD;
}
