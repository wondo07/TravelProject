package com.example.TravelProject.Sleep.Repository.queryDsl;

import com.example.TravelProject.Course.Entity.QCourse;
import com.example.TravelProject.Sleep.Entity.QSleep;
import com.example.TravelProject.Sleep.Entity.Sleep;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.Sleep.Entity.QSleep.sleep;

public class SleepRepositoryCustomImpl implements SleepRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public SleepRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Sleep findByIdWithQueryDsl(Long sleepId) {
        return queryFactory
                .select(sleep)
                .from(sleep)
                .join(sleep.course, QCourse.course).fetchJoin()
                .where(sleep.sleepId.eq(sleepId))
                .fetchOne();
    }
}
