package com.example.TravelProject.CourseData.Controller;

import com.example.TravelProject.CourseData.Dto.CourseDataPatchDto;
import com.example.TravelProject.CourseData.Dto.CourseDataPostDto;
import com.example.TravelProject.CourseData.Dto.CourseDataResponseDto;
import com.example.TravelProject.CourseData.Service.CourseDataService;
import com.example.TravelProject.Dtos.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coursedata")
public class CourseDataController {

    private final CourseDataService courseDataService;

    @PostMapping
    private ResponseEntity post(@RequestBody CourseDataPostDto courseDataPostDto){
        CourseDataResponseDto courseDataResponseDto = courseDataService.post(courseDataPostDto);
        return new ResponseEntity<>(courseDataResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("{courseDataId}")
    private ResponseEntity get(@PathVariable("courseDataId") Long courseDataId){
        CourseDataResponseDto courseDataResponseDto = courseDataService.get(courseDataId);
        return new ResponseEntity<>(courseDataResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){
        PageResponseDto pageResponseDto = courseDataService.gets(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{courseDataId}")
    private ResponseEntity patch(@PathVariable("courseDataId") Long courseDataId,
                                 @RequestBody CourseDataPatchDto courseDataPatchDto){
        CourseDataResponseDto courseDataResponseDto =
                courseDataService.patch(courseDataId, courseDataPatchDto);

        return new ResponseEntity<>(courseDataResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{courseDataId}")
    private ResponseEntity delete(@PathVariable("courseDataId") Long courseDataId){
        courseDataService.delete(courseDataId);
        return ResponseEntity.noContent().build();
    }
}
