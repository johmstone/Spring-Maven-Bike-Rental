package com.example.demo.security.services;

import java.util.List;

import com.example.demo.model.Bike;
import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BikeRequest;

public interface BikeDetailsImpl {

    Bike getBikeByID(Long id);

    List<Bike> getBikes();

    List<UserBikes> getBikesByUser(String id);

    List<Bike> getBikesShortname(String shortname);

    Bike createBike(BikeRequest request);
}
