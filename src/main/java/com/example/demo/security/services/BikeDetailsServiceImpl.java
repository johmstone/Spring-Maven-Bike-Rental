package com.example.demo.security.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bike;
import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BikeRequest;
import com.example.demo.repository.BikeRepository;
import com.example.demo.repository.UserBikesRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.util.StringUtils;

@Service
public class BikeDetailsServiceImpl implements BikeDetailsImpl {

    @Autowired
    BikeRepository bikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBikesRepository userBikesRepository;

    @Override
    public Bike getBikeByID(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bike> getBikes() {
        List<Bike> bikes = bikeRepository.findAll();
        return bikes.isEmpty() ? null : bikes;
    }

    @Override
    public List<Bike> getBikesShortname(String shortname) {
        List<Bike> bikes = bikeRepository.findByShortname(shortname);
        return bikes.isEmpty() ? null : bikes;
    }

    @Override
    public Bike createBike(BikeRequest request) {
        List<Bike> existBike = bikeRepository.findByShortname(request.getShortname().trim());

        if(request != null
            && StringUtils.hasLength(request.getBrand().trim())
            && (request.getSize() > 0)
            && StringUtils.hasLength(request.getStyle().trim())
            && StringUtils.hasLength(request.getShortname().trim())
            && existBike.size() == 0
            ) {
                Bike bike = Bike.builder()
                    .brand(request.getBrand().trim())
                    .size(request.getSize())
                    .style(request.getStyle().trim())
                    .shortname(request.getShortname().trim())
                    .build();
                return bikeRepository.save(bike);
            } else {
                return null;
            }
    }

    @Override
    public List<UserBikes> getBikesByUser(String id) {
        // User user = userRepository.findById(Long.valueOf(id)).orElse(null);
        List<UserBikes> data = userBikesRepository.findByUserid(Long.valueOf(id));
        return data;
    }
}
