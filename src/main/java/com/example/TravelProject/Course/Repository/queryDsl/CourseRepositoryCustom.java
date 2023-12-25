package com.example.TravelProject.Course.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.Course;

public interface CourseRepositoryCustom {

    Course findByIdWithQueryDsl(Long courseId);
}
