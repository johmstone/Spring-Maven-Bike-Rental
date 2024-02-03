package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bike;
import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BookBikeRequest;
import com.example.demo.payload.response.UserBikesResponse;
import com.example.demo.security.services.BikeDetailsImpl;
import com.example.demo.security.services.BookBikesImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final BikeDetailsImpl services;

    private final BookBikesImpl bookServices;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserBikesResponse> getProduct(@RequestBody BookBikeRequest request) {

        UserBikes userBikes = bookServices.BookBike(request);

        if (userBikes != null) {

            List<UserBikes> bikes = services.getBikesByUser(userBikes.getUserid().toString());
            List<Bike> data = bikes.stream()
                    .map(userBike -> services.getBikeByID(userBike.getBikeid()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(new UserBikesResponse(
                    userBikes.getUserid(),
                    data));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
