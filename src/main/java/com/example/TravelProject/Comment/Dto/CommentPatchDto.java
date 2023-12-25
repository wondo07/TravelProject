package com.example.TravelProject.Comment.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentPatchDto {

    private String content;

    private Long userId;

    private Long courseId;
}
