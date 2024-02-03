package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bike;
import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BikeRequest;
import com.example.demo.payload.response.UserBikesResponse;
import com.example.demo.security.services.BikeDetailsImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bikes")
public class BikeController {

    private final BikeDetailsImpl services;

    @GetMapping("/all")
    public ResponseEntity<List<Bike>> allBikes() {
        List<Bike> bikes = services.getBikes();
        if (bikes != null) {
            return ResponseEntity.ok(bikes);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bike> getProduct(@RequestBody BikeRequest request) {

        Bike createBike = services.createBike(request);

        if (createBike != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createBike);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{UserID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserBikesResponse> getBikesByUser(@PathVariable String UserID) {

        List<UserBikes> bikes = services.getBikesByUser(UserID);

        if (bikes != null) {

            List<Bike> data = bikes.stream()
                    .map(userBike -> services.getBikeByID(userBike.getBikeid()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(new UserBikesResponse(
                    Long.valueOf(UserID),
                    data));
        } else {
            return ResponseEntity.ok(null);
        }
    }
}
