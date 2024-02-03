package com.example.demo.security.services;

import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BookBikeRequest;

public interface BookBikesImpl {
    UserBikes BookBike(BookBikeRequest request);
}
