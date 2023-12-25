package com.example.TravelProject.Course.Entity;

import com.example.TravelProject.Comment.Entity.Comment;
import com.example.TravelProject.Course.Dto.CoursePostDto;
import com.example.TravelProject.CourseData.Entity.CourseData;
import com.example.TravelProject.CourseLike.Entity.CourseLike;
import com.example.TravelProject.Eat.Entity.Eat;
import com.example.TravelProject.PathCoordinates.Entity.PathCoordinates;
import com.example.TravelProject.Sleep.Entity.Sleep;
import com.example.TravelProject.Travel.Entity.Travel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column
    @Setter
    private String courseName;

    @Column
    @Setter
    private Integer viewCount;

    @Column
    @Setter
    private String tag;

    @Column
    @Setter
    private String guideName;

    @Column
    @Setter
    private String guideText;

    @Column
    @Setter
    private Integer likeCount;

    @Column
    @Setter
    private String location;

    @Column
    @Setter
    private Integer time;

    @Column
    @Setter
    private String route;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseData> courseDataList = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseLike> courseLikes = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Travel> travels = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Eat> eats = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Sleep> sleeps = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<PathCoordinates> pathCoordinatesList = new ArrayList<>();

    public void addPathCoordinates(PathCoordinates pathCoordinates){
        pathCoordinatesList.add(pathCoordinates);
        pathCoordinates.setCourse(this);
    }

    public void addSleep(Sleep sleep){
        sleeps.add(sleep);
        sleep.setCourse(this);
    }

    public void addCourseData(CourseData courseData){
        courseDataList.add(courseData);
        courseData.setCourse(this);
    }

    public void addEat(Eat eat){
        eat.setCourse(this);
        eats.add(eat);
    }
    public void addCourseLike(CourseLike courseLike){
        courseLike.setCourse(this);
        courseLikes.add(courseLike);
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setCourse(this);
    }

    public void addTravel(Travel travel){
        travels.add(travel);
        travel.setCourse(this);
    }

    public Course(CoursePostDto coursePostDto) {
        this.courseName = coursePostDto.getCourseName();
        this.tag = coursePostDto.getTag();
        this.guideName = coursePostDto.getGuideName();
        this.guideText = coursePostDto.getGuideText();
        this.location = coursePostDto.getLocation();
        this.time = coursePostDto.getTime();
        this.route = coursePostDto.getRoute();
    }
}
