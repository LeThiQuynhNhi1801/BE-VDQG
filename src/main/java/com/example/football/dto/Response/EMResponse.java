package com.example.football.dto.Response;

import com.example.football.entity.EventMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMResponse {
    private String name;
    private List<String> ghiban;

    private List<String> phanluoinha;

    private List<String> thevang;

    private List<String> thedo;

    private Integer loi;

    private Integer phatgoc;

    private List<String> rasan;

    private List<String> vaosan;
}
