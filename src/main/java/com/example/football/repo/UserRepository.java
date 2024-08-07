package com.example.football.repo;

import com.example.football.entity.Team;
import com.example.football.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.userName =:username")
    List<User> findByUserName(@Param("username") String username);

    Optional<User> findUserByUserName(String userName);
}
