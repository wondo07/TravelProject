package com.example.TravelProject.CourseData.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.QCourse;
import com.example.TravelProject.CourseData.Entity.CourseData;
import com.example.TravelProject.CourseData.Entity.QCourseData;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.CourseData.Entity.QCourseData.courseData;

public class CourseDataRepositoryCustomImpl implements CourseDataRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public CourseDataRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CourseData findByIdWithQueryDsl(Long courseDataId) {
        return queryFactory
                .select(courseData)
                .from(courseData)
                .join(courseData.course, QCourse.course).fetchJoin()
                .where(courseData.courseDataId.eq(courseDataId))
                .fetchOne();
    }
}
