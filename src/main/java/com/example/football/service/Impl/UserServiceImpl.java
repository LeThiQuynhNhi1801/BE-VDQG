package com.example.football.service.Impl;

import com.example.football.dto.Request.LoginRequest;
import com.example.football.dto.Response.LoginResponse;
import com.example.football.entity.User;
import com.example.football.exception.BaseException;
import com.example.football.repo.UserRepository;
import com.example.football.sercurity.CustomUserDetailsService;
import com.example.football.sercurity.JWTUtility;
import com.example.football.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility jwtUtility;
    private final CustomUserDetailsService userDetailsService;
    @Override
    public LoginResponse Login(LoginRequest loginRequest) {
        try {
            User user = userRepository.findUserByUserName(loginRequest.getUserName()).orElse(null);

            System.out.println("Pass: " + passwordEncoder.encode(loginRequest.getPassWord()));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord()));

//            User user = userRepository.findUserByUserName(loginRequest.getUserName()).orElse(null);
            if (!passwordEncoder.matches(loginRequest.getPassWord(), user.getPassword())) {
                throw new BaseException();
            }

            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
            String token = jwtUtility.generateToken(userDetails);
            return new LoginResponse(user.getIdUser(),user.getTeam().getIdTeam(),token,userDetails.getAuthorities().toString());
        } catch (Exception e) {
            throw new BaseException();
        }
    }
}
