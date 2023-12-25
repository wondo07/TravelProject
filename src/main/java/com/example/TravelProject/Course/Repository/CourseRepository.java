package com.example.TravelProject.Course.Repository;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Repository.queryDsl.CourseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {
}
