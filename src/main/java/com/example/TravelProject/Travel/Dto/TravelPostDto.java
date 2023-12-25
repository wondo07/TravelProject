package com.example.TravelProject.Travel.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelPostDto {

    private String name;

    private String lat;

    private String lng;

    private Long courseId;
}
