package com.example.TravelProject.Eat.Entity;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Eat.Dto.EatPostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Eat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eatId;

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

    public Eat(EatPostDto eatPostDto) {
        this.name = eatPostDto.getName();
        this.lat = eatPostDto.getLat();
        this.lng = eatPostDto.getLng();
    }
}
