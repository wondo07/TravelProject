package com.example.TravelProject.Sleep.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SleepPostDto {

    private String name;

    private String lat;

    private String lng;

    private Long courseId;

}
