package com.example.TravelProject.Course.Controller;

import com.example.TravelProject.Course.Dto.CoursePatchDto;
import com.example.TravelProject.Course.Dto.CoursePostDto;
import com.example.TravelProject.Course.Dto.CourseResponseDto;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.Dtos.PageResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/course")
@RestController
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    private ResponseEntity post(@RequestBody CoursePostDto coursePostDto){

        CourseResponseDto courseResponseDto = courseService.post(coursePostDto);

        return new ResponseEntity<>(courseResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}")
    private ResponseEntity get(@PathVariable("courseId") Long courseId){

        CourseResponseDto courseResponseDto = courseService.get(courseId);

        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){

        PageResponseDto pageResponseDto = courseService.gets(pageable);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{courseId}")
    private ResponseEntity patch(@RequestBody CoursePatchDto coursePatchDto,
                                 @PathVariable("courseId") Long couresId){

        CourseResponseDto courseResponseDto = courseService.patch(coursePatchDto, couresId);

        return new ResponseEntity<>(courseResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    private ResponseEntity delete(@PathVariable("courseId") Long courseId){
        courseService.delete(courseId);
        return ResponseEntity.noContent().build();
    }
}
