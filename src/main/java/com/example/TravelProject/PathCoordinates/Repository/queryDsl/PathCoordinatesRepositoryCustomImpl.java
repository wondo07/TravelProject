package com.example.TravelProject.PathCoordinates.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.QCourse;
import com.example.TravelProject.PathCoordinates.Entity.PathCoordinates;
import com.example.TravelProject.PathCoordinates.Entity.QPathCoordinates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.PathCoordinates.Entity.QPathCoordinates.pathCoordinates;

public class PathCoordinatesRepositoryCustomImpl implements PathCoordinatesRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public PathCoordinatesRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PathCoordinates findByIdWithQueryDsl(Long pathCoordinatesId) {
        return queryFactory
                .select(pathCoordinates)
                .from(pathCoordinates)
                .join(pathCoordinates.course, QCourse.course).fetchJoin()
                .where(pathCoordinates.PathCoordinatesId.eq(pathCoordinatesId))
                .fetchOne();
    }
}
