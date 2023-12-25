package com.example.TravelProject.Sleep.Service;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import com.example.TravelProject.Sleep.Dto.SleepPatchDto;
import com.example.TravelProject.Sleep.Dto.SleepPostDto;
import com.example.TravelProject.Sleep.Dto.SleepResponseDto;
import com.example.TravelProject.Sleep.Entity.Sleep;
import com.example.TravelProject.Sleep.Repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepository sleepRepository;
    private final CourseService courseService;

    public SleepResponseDto post(SleepPostDto sleepPostDto) {
        Sleep sleep = toEntity(sleepPostDto);
        Course course = courseService.verifiedCourse(sleepPostDto.getCourseId());
        course.addSleep(sleep);
        Sleep save = sleepRepository.save(sleep);
        return toResponseDto(save);

    }

    public SleepResponseDto get(Long sleepId) {
        Sleep sleep = verifiedSleepWithQueryDsl(sleepId);
        return toResponseDto(sleep);
    }

    private Sleep verifiedSleep(Long sleepId) {
        Optional<Sleep> optionalSleep =
                sleepRepository.findById(sleepId);
        return optionalSleep.orElseThrow(
                () -> new BusinessException(ErrorCode.SLEEP_NOT_FOUND));
    }

    private Sleep verifiedSleepWithQueryDsl(Long sleepId) {
        Sleep sleep = sleepRepository.findByIdWithQueryDsl(sleepId);
        if(sleep == null){
            throw new BusinessException(ErrorCode.SLEEP_NOT_FOUND);
        }
        return sleep;
    }

    public PageResponseDto gets(Pageable pageable) {
        Page<Sleep> sleepPage = sleepRepository.findAll(pageable);
        List<Sleep> sleepList = sleepPage.getContent();
        return PageResponseDto.of(sleepList,
                new PageImpl(sleepList,
                        sleepPage.getPageable(),
                        sleepPage.getTotalElements()));
    }

    public SleepResponseDto patch(Long sleepId, SleepPatchDto sleepPatchDto) {
        Sleep sleep = verifiedSleepWithQueryDsl(sleepId);

        Optional.ofNullable(sleepPatchDto.getName())
                .ifPresent(sleep::setName);

        Optional.ofNullable(sleepPatchDto.getLat())
                .ifPresent(sleep::setLat);

        Optional.ofNullable(sleepPatchDto.getLng())
                .ifPresent(sleep::setLng);

        Sleep save = sleepRepository.save(sleep);

        return toResponseDto(save);
    }

    private Sleep toEntity(SleepPostDto sleepPostDto){
        return new Sleep(sleepPostDto);
    }

    private SleepResponseDto toResponseDto(Sleep sleep){
        return new SleepResponseDto(sleep);
    }

    public void delete(Long sleepId) {
        sleepRepository.deleteById(sleepId);
    }
}
