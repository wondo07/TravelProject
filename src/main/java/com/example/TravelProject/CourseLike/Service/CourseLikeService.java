package com.example.TravelProject.CourseLike.Service;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.CourseLike.Dto.CourseLikePostDto;
import com.example.TravelProject.CourseLike.Dto.CourseLikeResponseDto;
import com.example.TravelProject.CourseLike.Entity.CourseLike;
import com.example.TravelProject.CourseLike.Repository.CourseLikeRepository;
import com.example.TravelProject.User.Entity.User;
import com.example.TravelProject.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseLikeService {

    private final CourseLikeRepository courseLikeRepository;
    private final CourseService courseService;
    private UserService userService;

    public CourseLikeResponseDto post(CourseLikePostDto courseLikePostDto, Long courseId) {
        Course course = courseService.verifiedCourse(courseId);
        User user = userService.verifiedUser(courseLikePostDto.getUserId());

        if(courseLikeRepository.findByUserAndCourse(user,course) == null){
            CourseLike courseLike = new CourseLike();
            int idx = courseLikeRepository.findAll().size();
            courseLike.setCourseLikeId((long) idx+1);
            courseLike.setCourseLikeStatus(1);
            user.addCourseLike(courseLike);
            course.addCourseLike(courseLike);
            CourseLike save = courseLikeRepository.save(courseLike);
            return toResponseDto(save);
        } else{
            CourseLike courseLike = courseLikeRepository.findByUserAndCourse(user, course);
            if(courseLike.getCourseLikeStatus() == 1){
                courseLike.setCourseLikeStatus(0);
                CourseLike save = courseLikeRepository.save(courseLike);
                return toResponseDto(save);
            } else {
                courseLike.setCourseLikeStatus(1);
                CourseLike save = courseLikeRepository.save(courseLike);
                return toResponseDto(save);
            }
        }
    }

    public CourseLikeResponseDto toResponseDto(CourseLike courseLike){
        return new CourseLikeResponseDto(courseLike.getCourseLikeId(), courseLike.getCourseLikeStatus());
    }
}
