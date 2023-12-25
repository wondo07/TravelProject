package com.example.TravelProject.Sleep.Entity;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Sleep.Dto.SleepPostDto;
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
public class Sleep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sleepId;

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
    @JoinColumn(name = "courseId")
    @JsonIgnore
    @Setter
    private Course course;

    public Sleep(SleepPostDto sleepPostDto) {
        this.name = sleepPostDto.getName();
        this.lat = sleepPostDto.getLat();
        this.lng = sleepPostDto.getLng();
    }
}
