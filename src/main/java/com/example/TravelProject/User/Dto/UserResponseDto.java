package com.example.TravelProject.User.Dto;

import com.example.TravelProject.Comment.Dto.CommentResponseDto;
import com.example.TravelProject.CourseLike.Dto.CourseLikeResponseDto;
import com.example.TravelProject.Enum.UserStatus;
import com.example.TravelProject.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private String name;

    private String email;

    private String password;

    private UserStatus userStatus;

    private LocalDateTime cratedAt;

    private LocalDateTime modifiedAt;

    private Integer likeCount;

    private List<CommentResponseDto> commentResponseDtos;

    private List<CourseLikeResponseDto> courseLikeResponseDtos;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userStatus = user.getUserStatus();
        this.cratedAt = user.getCratedAt();
        this.modifiedAt = user.getModifiedAt();
        this.likeCount = user.getLikeCount();
    }
}
