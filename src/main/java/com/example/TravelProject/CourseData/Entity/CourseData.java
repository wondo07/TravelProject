package com.example.TravelProject.CourseData.Entity;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.CourseData.Dto.CourseDataPostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CourseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseDataId;

    @Column
    @Setter
    private String title;

    @Column
    @Setter
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "courseId")
    @Setter
    private Course course;

    public CourseData(CourseDataPostDto courseDataPostDto) {
        this.title = courseDataPostDto.getTitle();
        this.text = courseDataPostDto.getText();
    }
}
