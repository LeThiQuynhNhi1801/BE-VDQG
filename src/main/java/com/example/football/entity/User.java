package com.example.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer idUser;

    private String userName;

    private String Password;

    @ManyToOne
    private Role role;

    @OneToOne
    private Team team;
}
