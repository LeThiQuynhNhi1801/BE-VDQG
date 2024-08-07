package com.example.football.service.Impl;

import com.example.football.dto.Request.AddTeamRequest;
import com.example.football.dto.Response.TeamResponse;
import com.example.football.entity.Player;
import com.example.football.entity.Role;
import com.example.football.entity.Team;
import com.example.football.entity.User;
import com.example.football.repo.PlayerRepository;
import com.example.football.repo.RoleRepository;
import com.example.football.repo.TeamRepository;
import com.example.football.repo.UserRepository;
import com.example.football.service.TeamService;
import com.example.football.untils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Override
    public TeamResponse addTeam(AddTeamRequest addTeamRequest) {
        Team team = new Team();
        team.setName(addTeamRequest.getName());
        team.setLogo(addTeamRequest.getLogo());
        team.setPhoneManager(addTeamRequest.getPhoneManager());
        team.setEmailManager(addTeamRequest.getEmailManager());
        teamRepository.save(team);
        Role role = roleRepository.findByNameRole("ROLE_MANAGER");
        User user = new User(null,addTeamRequest.getEmailManager(),passwordEncoder.encode(addTeamRequest.getPhoneManager()),role,team);
        userRepository.save(user);
        return MapperUtils.toDTO(team,TeamResponse.class);
    }

    @Override
    public List<TeamResponse> allTeam() {
        return MapperUtils.toDTOs(teamRepository.findAll(),TeamResponse.class);
    }
}
