package com.example.TravelProject.Eat.Service;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Eat.Dto.EatPatchDto;
import com.example.TravelProject.Eat.Dto.EatPostDto;
import com.example.TravelProject.Eat.Dto.EatResponseDto;
import com.example.TravelProject.Eat.Entity.Eat;
import com.example.TravelProject.Eat.Repository.EatRepository;
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
public class EatService {

    private final EatRepository eatRepository;
    private final CourseService courseService;

    public EatResponseDto post(EatPostDto eatPostDto) {
        Eat eat = toEntity(eatPostDto);
        Course course = courseService.verifiedCourse(eatPostDto.getCourseId());
        course.addEat(eat);
        Eat save = eatRepository.save(eat);
        return toRepository(save);
    }


    public EatResponseDto get(Long eatId) {
        Eat eat = verifiedEatWithQueryDsl(eatId);
        return toRepository(eat);
    }


    private Eat verifiedEat(Long eatId) {
        Optional<Eat> optionalEat = eatRepository.findById(eatId);
        return optionalEat.orElseThrow(
                () -> new BusinessException(ErrorCode.EAT_NOT_FOUND));
    }

    private Eat verifiedEatWithQueryDsl(Long eatId) {

        Eat eat = eatRepository.findByIdWithQueryDsl(eatId);

        if(eat == null){
            throw new BusinessException(ErrorCode.EAT_NOT_FOUND);
        }

        return eat;
    }

    private Eat toEntity(EatPostDto eatPostDto){
        return new Eat(eatPostDto);
    }

    private EatResponseDto toRepository(Eat eat){
        return new EatResponseDto(eat);
    }


    public PageResponseDto gets(Pageable pageable) {
        Page<Eat> eatPage = eatRepository.findAll(pageable);
        List<Eat> eatList = eatPage.getContent();
        return PageResponseDto.of(eatList,
                new PageImpl(eatList,
                        eatPage.getPageable(),
                        eatPage.getTotalElements()));
    }

    public EatResponseDto patch(Long eatId, EatPatchDto eatPatchDto) {
        Eat eat = verifiedEatWithQueryDsl(eatId);

        Optional.ofNullable(eatPatchDto.getName())
                .ifPresent(eat::setName);

        Optional.ofNullable(eatPatchDto.getLat())
                .ifPresent(eat::setLat);

        Optional.ofNullable(eatPatchDto.getLng())
                .ifPresent(eat::setLng);

        Eat save = eatRepository.save(eat);

        return toRepository(save);
    }

    public void delete(Long eatId) {
        eatRepository.deleteById(eatId);
    }
}
