package com.example.TravelProject.CourseData.Dto;

import com.example.TravelProject.CourseData.Entity.CourseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDataResponseDto {

    private String title;

    private String text;

    public CourseDataResponseDto(CourseData courseData) {
        this.title = courseData.getTitle();
        this.text = courseData.getText();
    }
}
