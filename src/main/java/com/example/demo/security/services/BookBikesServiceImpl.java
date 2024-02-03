package com.example.demo.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.UserBikes;
import com.example.demo.payload.request.BookBikeRequest;
import com.example.demo.repository.UserBikesRepository;
import org.springframework.stereotype.Service;

@Service
public class BookBikesServiceImpl implements BookBikesImpl {

    @Autowired
    UserBikesRepository userBikesRepository;

    @Override
    public UserBikes BookBike(BookBikeRequest request) {
        if(request != null
            && (request.getUserid() > 0)
            && (request.getBikeid() > 0)
            ) {
                UserBikes book = UserBikes.builder()
                    .userid(request.getUserid())
                    .bikeid(request.getBikeid())
                    .build();
                return userBikesRepository.save(book);
            } else {
                return null;
            }
    }

}
