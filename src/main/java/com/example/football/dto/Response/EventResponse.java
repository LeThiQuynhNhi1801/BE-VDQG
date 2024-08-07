package com.example.football.dto.Response;

import com.example.football.entity.EventMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private List<String> namePlayer;
}
