package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.model.User;
import com.example.demo.model.UserBikes;

public interface UserBikesRepository extends JpaRepository<UserBikes, Long> {
    List<UserBikes> findByUserid(Long userid);
}
