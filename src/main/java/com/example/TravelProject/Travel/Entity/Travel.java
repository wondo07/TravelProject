package com.example.TravelProject.Travel.Entity;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Travel.Dto.TravelPostDto;
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
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;

    @Column
    @Setter
    private String name;

    @Column
    @Setter
    private String lat;

    @Column
    @Setter
    private String lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "courseId")
    @Setter
    private Course course;

    public Travel(TravelPostDto travelPostDto) {
        this.name = travelPostDto.getName();
        this.lat = travelPostDto.getLat();
        this.lng = travelPostDto.getLng();
    }
}
