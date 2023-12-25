package com.example.TravelProject.CourseData.Service;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.CourseData.Dto.CourseDataPatchDto;
import com.example.TravelProject.CourseData.Dto.CourseDataPostDto;
import com.example.TravelProject.CourseData.Dto.CourseDataResponseDto;
import com.example.TravelProject.CourseData.Entity.CourseData;
import com.example.TravelProject.CourseData.Repository.CourseDataRepository;
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
public class CourseDataService {

    private final CourseDataRepository courseDataRepository;
    private final CourseService courseService;

    public CourseDataResponseDto post(CourseDataPostDto courseDataPostDto) {
        CourseData courseData = toEntity(courseDataPostDto);
        Course course = courseService.verifiedCourseWithQueryDsl(courseDataPostDto.getCourseId());
        course.addCourseData(courseData);
        CourseData save = courseDataRepository.save(courseData);
        return toResponseDto(save);
    }

    public CourseDataResponseDto get(Long courseDataId) {
        CourseData courseData = verifiedCourseDataWithQueryDsl(courseDataId);
        return toResponseDto(courseData);
    }

    public PageResponseDto gets(Pageable pageable) {
        Page<CourseData> courseDataPage = courseDataRepository.findAll(pageable);
        List<CourseData> courseDataList = courseDataPage.getContent();
        return PageResponseDto.of(courseDataList,
                new PageImpl(courseDataList,
                        courseDataPage.getPageable(),
                        courseDataPage.getTotalElements()));
    }

    public CourseDataResponseDto patch(Long courseDataId, CourseDataPatchDto courseDataPatchDto) {
        CourseData courseData = verifiedCourseDataWithQueryDsl(courseDataId);

        Optional.ofNullable(courseDataPatchDto.getTitle())
                .ifPresent(courseData::setTitle);

        Optional.ofNullable(courseDataPatchDto.getText())
                .ifPresent(courseData::setText);

        CourseData save = courseDataRepository.save(courseData);

        return toResponseDto(save);
    }

    public void delete(Long courseDataId) {
        courseDataRepository.deleteById(courseDataId);
    }

    private CourseData verifiedCourseData(Long courseDataId) {
        Optional<CourseData> optionalCourseData = courseDataRepository.findById(courseDataId);
        return optionalCourseData.orElseThrow(
                () -> new BusinessException(ErrorCode.COURSEDATA_NOT_FOUND));
    }

    private CourseData verifiedCourseDataWithQueryDsl(Long courseDataId) {
        CourseData courseData = courseDataRepository.findByIdWithQueryDsl(courseDataId);
        if(courseData == null){
            throw new BusinessException(ErrorCode.COURSEDATA_NOT_FOUND);
        }
        return courseData;
    }
    private CourseData toEntity(CourseDataPostDto courseDataPostDto){
        return new CourseData(courseDataPostDto);
    }

    private CourseDataResponseDto toResponseDto(CourseData courseData){
        return new CourseDataResponseDto(courseData);
    }

}
