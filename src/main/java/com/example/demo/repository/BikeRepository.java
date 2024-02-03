package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long>{
    List<Bike> findByShortname(String shortname);    
}
