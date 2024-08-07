package com.example.football.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Referees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idReferee;

    private String nameReferee;

    private String phone;

    private Date DOB;

    private String address;

    private String CCCD;

    private String status;
}
