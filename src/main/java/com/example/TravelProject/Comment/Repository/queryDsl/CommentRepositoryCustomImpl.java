package com.example.TravelProject.Comment.Repository.queryDsl;

import com.example.TravelProject.Comment.Entity.Comment;
import com.example.TravelProject.Comment.Entity.QComment;
import com.example.TravelProject.Course.Entity.QCourse;
import com.example.TravelProject.User.Entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.example.TravelProject.Comment.Entity.QComment.comment;
import static com.example.TravelProject.Course.Entity.QCourse.course;

public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public CommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Comment findByIdWithQueryDsl(Long commentId) {
        return queryFactory
                .select(comment)
                .from(comment)
                .join(comment.user, QUser.user).fetchJoin()
                .join(comment.course, course).fetchJoin()
                .where(comment.commentId.eq(commentId))
                .fetchOne();
    }
}
