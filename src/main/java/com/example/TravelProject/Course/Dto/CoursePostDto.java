package com.example.TravelProject.Course.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoursePostDto {

    private String courseName;

    private String tag;

    private String guideName;

    private String guideText;

    private String location;

    private Integer time;

    private String route;
}
