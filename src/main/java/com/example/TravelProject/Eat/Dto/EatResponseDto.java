package com.example.TravelProject.Eat.Dto;

import com.example.TravelProject.Eat.Entity.Eat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EatResponseDto {

    private String name;

    private String lat;

    private String lng;

    public EatResponseDto(Eat eat) {
        this.name = eat.getName();
        this.lat = eat.getLat();
        this.lng = eat.getLng();
    }
}
