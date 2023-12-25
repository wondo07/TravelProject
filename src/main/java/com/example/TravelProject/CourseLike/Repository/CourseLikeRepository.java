package com.example.TravelProject.CourseLike.Repository;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.CourseLike.Entity.CourseLike;
import com.example.TravelProject.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {

    CourseLike findByUserAndCourse(User user, Course course);
}
