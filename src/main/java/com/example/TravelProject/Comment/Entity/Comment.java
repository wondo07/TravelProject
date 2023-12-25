package com.example.TravelProject.Comment.Entity;

import com.example.TravelProject.Comment.Dto.CommentPostDto;
import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    @Setter
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter
    @JoinColumn(name = "courseId")
    private Course course;

    public Comment(CommentPostDto commentPostDto) {
        this.content = content;
    }
}
