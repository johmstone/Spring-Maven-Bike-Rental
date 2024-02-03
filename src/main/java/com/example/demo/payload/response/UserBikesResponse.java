package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.example.demo.model.Bike;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBikesResponse {
    private Long userid;
	private List<Bike> bikes;
}
