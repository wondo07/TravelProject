package com.example.TravelProject.Course.Dto;

import com.example.TravelProject.Course.Entity.Course;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseDto {

    private String courseName;

    private Integer viewCount;

    private String tag;

    private String guideName;

    private String guideText;

    private Integer likeCount;

    private String location;

    private Integer time;

    private String route;

    public CourseResponseDto(Course course) {
        this.courseName = course.getCourseName();
        this.viewCount = course.getViewCount();
        this.tag = course.getTag();
        this.guideName = course.getGuideName();
        this.guideText = course.getGuideText();
        this.likeCount = course.getLikeCount();
        this.location = course.getLocation();
        this.time = course.getTime();
        this.route = course.getRoute();
    }
}
