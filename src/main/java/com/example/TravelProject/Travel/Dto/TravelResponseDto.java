package com.example.TravelProject.Travel.Dto;

import com.example.TravelProject.Travel.Entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelResponseDto {

    private String name;

    private String lat;

    private String lng;

    public TravelResponseDto(Travel travel) {
        this.name = travel.getName();
        this.lat = travel.getLat();
        this.lng = travel.getLng();
    }
}
