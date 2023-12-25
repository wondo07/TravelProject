package com.example.TravelProject.Comment.Repository;

import com.example.TravelProject.Comment.Entity.Comment;
import com.example.TravelProject.Comment.Repository.queryDsl.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
