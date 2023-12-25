package com.example.TravelProject.CourseData.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDataPostDto {

    private String title;

    private String text;

    private Long courseId;
}
