package com.example.TravelProject.PathCoordinates.Entity;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesPostDto;
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
public class PathCoordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pathCoordinatesId;

    @Setter
    @Column
    private String lat;

    @Setter
    @Column
    private String lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @JsonIgnore
    @Setter
    private Course course;

    public PathCoordinates(PathCoordinatesPostDto pathCoordinatesPostDto) {
        this.lat = pathCoordinatesPostDto.getLat();
        this.lng = pathCoordinatesPostDto.getLng();
    }
}
