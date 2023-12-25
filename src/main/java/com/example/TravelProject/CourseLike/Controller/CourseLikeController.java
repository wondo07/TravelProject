package com.example.TravelProject.CourseLike.Controller;

import com.example.TravelProject.CourseLike.Dto.CourseLikePostDto;
import com.example.TravelProject.CourseLike.Dto.CourseLikeResponseDto;
import com.example.TravelProject.CourseLike.Repository.CourseLikeRepository;
import com.example.TravelProject.CourseLike.Service.CourseLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseLike")
@RequiredArgsConstructor

public class CourseLikeController {

    private final CourseLikeService courseLikeService;

    @PostMapping("/{courseId}")
    private ResponseEntity post(@PathVariable Long courseId,
                                @RequestBody CourseLikePostDto courseLikePostDto){
        CourseLikeResponseDto courseLikeResponseDto = courseLikeService.post(courseLikePostDto, courseId);
        return new ResponseEntity<>(courseLikeResponseDto, HttpStatus.OK);
    }
}
