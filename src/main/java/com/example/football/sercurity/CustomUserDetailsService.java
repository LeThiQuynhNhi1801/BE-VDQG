package com.example.football.sercurity;
import com.example.football.entity.User;
import com.example.football.entity.Role;
import com.example.football.repo.RoleRepository;
import com.example.football.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByUserName(username);
        Role userRoles = user.get().getRole();
        List<Role> a = new ArrayList<>();
        a.add(userRoles);
        // Tạo danh sách quyền từ role của Employee
        List<GrantedAuthority> authorities = a.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNameRole()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.withUsername(user.get().getUserName())
                .password(user.get().getPassword())
                .authorities(authorities)
                .build();
    }


}