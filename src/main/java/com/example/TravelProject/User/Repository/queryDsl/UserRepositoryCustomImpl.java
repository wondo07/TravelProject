package com.example.TravelProject.User.Repository.queryDsl;

import com.example.TravelProject.User.Entity.QUser;
import com.example.TravelProject.User.Entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.User.Entity.QUser.user;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public User findByIdWithQueryDsl(Long userId) {
        return queryFactory
                .select(user)
                .from(user)
                .where(user.userId.eq(userId))
                .fetchOne();
    }
}
