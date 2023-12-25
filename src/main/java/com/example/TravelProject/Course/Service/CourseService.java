package com.example.TravelProject.Course.Service;

import com.example.TravelProject.Course.Dto.CoursePatchDto;
import com.example.TravelProject.Course.Dto.CoursePostDto;
import com.example.TravelProject.Course.Dto.CourseResponseDto;
import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Repository.CourseRepository;
import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseResponseDto post(CoursePostDto coursePostDto) {
        Course course = toEntity(coursePostDto);
        Course save = courseRepository.save(course);
        return toResponseDto(save);
    }

    public CourseResponseDto get(Long courseId) {
        Course course = verifiedCourseWithQueryDsl(courseId);
        return toResponseDto(course);
    }

    public PageResponseDto gets(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);
        List<Course> courseList = coursePage.getContent();
        return PageResponseDto.of(courseList,
                new PageImpl(courseList,
                        coursePage.getPageable(),
                        coursePage.getTotalElements()));
    }

    public CourseResponseDto patch(CoursePatchDto coursePatchDto, Long couresId) {
        Course course = verifiedCourseWithQueryDsl(couresId);

        Optional.ofNullable(coursePatchDto.getCourseName())
                .ifPresent(course::setCourseName);

        Optional.ofNullable(coursePatchDto.getGuideName())
                .ifPresent(course::setGuideName);

        Optional.ofNullable(coursePatchDto.getLocation())
                .ifPresent(course::setLocation);

        Optional.ofNullable(coursePatchDto.getGuideText())
                .ifPresent(course::setGuideText);

        Optional.ofNullable(coursePatchDto.getRoute())
                .ifPresent(course::setRoute);

        Optional.ofNullable(coursePatchDto.getTag())
                .ifPresent(course::setTag);

        Optional.ofNullable(coursePatchDto.getTime())
                .ifPresent(course::setTime);

        Course save = courseRepository.save(course);

        return toResponseDto(save);
    }

    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public Course verifiedCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElseThrow(
                () -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));
    }

    public Course verifiedCourseWithQueryDsl(Long courseId) {
        Course course = courseRepository.findByIdWithQueryDsl(courseId);
        if(course == null){
            throw new BusinessException(ErrorCode.COURSE_NOT_FOUND);
        }
        return course;
    }

    public Course toEntity(CoursePostDto coursePostDto){
        return new Course(coursePostDto);
    }

    public CourseResponseDto toResponseDto(Course course){
        return new CourseResponseDto(course);
    }



}
