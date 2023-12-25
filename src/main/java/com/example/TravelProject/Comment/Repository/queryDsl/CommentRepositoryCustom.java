package com.example.TravelProject.Comment.Repository.queryDsl;

import com.example.TravelProject.Comment.Entity.Comment;

public interface CommentRepositoryCustom {

    Comment findByIdWithQueryDsl(Long commentId);
}
