package com.example.TravelProject.Eat.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EatPatchDto {

    private String name;

    private String lat;

    private String lng;
}
