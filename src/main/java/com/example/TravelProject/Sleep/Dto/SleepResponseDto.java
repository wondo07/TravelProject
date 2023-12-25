package com.example.TravelProject.Sleep.Dto;

import com.example.TravelProject.Sleep.Entity.Sleep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SleepResponseDto {

    private String name;

    private String lat;

    private String lng;

    public SleepResponseDto(Sleep sleep) {
        this.name = sleep.getName();
        this.lat = sleep.getLat();
        this.lng = sleep.getLng();
    }
}
