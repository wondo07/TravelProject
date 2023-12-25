package com.example.TravelProject.Eat.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.QCourse;
import com.example.TravelProject.Eat.Entity.Eat;
import com.example.TravelProject.Eat.Entity.QEat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.Course.Entity.QCourse.course;
import static com.example.TravelProject.Eat.Entity.QEat.eat;

public class EatRepositoryCustomImpl implements EatRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public EatRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Eat findByIdWithQueryDsl(Long eatId) {
        return queryFactory
                .select(eat)
                .from(eat)
                .join(eat.course, course)
                .where(eat.eatId.eq(eatId))
                .fetchOne();
    }
}
