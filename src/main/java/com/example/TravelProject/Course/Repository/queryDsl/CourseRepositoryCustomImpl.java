package com.example.TravelProject.Course.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Entity.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.Course.Entity.QCourse.course;

public class CourseRepositoryCustomImpl implements CourseRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public CourseRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Course findByIdWithQueryDsl(Long courseId) {
        return queryFactory
                .select(course)
                .from(course)
                .where(course.courseId.eq(courseId))
                .fetchOne();
    }
}
