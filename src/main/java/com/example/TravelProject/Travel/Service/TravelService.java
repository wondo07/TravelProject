package com.example.TravelProject.Travel.Service;

import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import com.example.TravelProject.Travel.Dto.TravelPatchDto;
import com.example.TravelProject.Travel.Dto.TravelPostDto;
import com.example.TravelProject.Travel.Dto.TravelResponseDto;
import com.example.TravelProject.Travel.Entity.Travel;
import com.example.TravelProject.Travel.Repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final CourseService courseService;

    public TravelResponseDto post(TravelPostDto travelPostDto) {
        Travel travel = toEntity(travelPostDto);
        Course course = courseService.verifiedCourse(travelPostDto.getCourseId());
        course.addTravel(travel);
        Travel save = travelRepository.save(travel);

        return toResponseDto(save);

    }

    public TravelResponseDto get(Long travelId) {
        Travel travel = verifiedTravel(travelId);
        return toResponseDto(travel);
    }

    public Travel verifiedTravel(Long travelId){
        Optional<Travel> optionalTravel = travelRepository.findById(travelId);
        return optionalTravel.orElseThrow(
                () -> new BusinessException(ErrorCode.TRAVEL_NOT_FOUND));

    }
    public Travel toEntity(TravelPostDto travelPostDto){
        return new Travel(travelPostDto);
    }

    public TravelResponseDto toResponseDto(Travel travel){
        return new TravelResponseDto(travel);
    }


    public PageResponseDto gets(Pageable pageable) {
        Page<Travel> travelPage = travelRepository.findAll(pageable);
        List<Travel> travelList = travelPage.getContent();
        return PageResponseDto.of(travelList,
                new PageImpl(travelList,
                        travelPage.getPageable(),
                        travelPage.getTotalElements()));
    }

    public TravelResponseDto patch(Long travelId, TravelPatchDto travelPatchDto) {

        Travel travel = verifiedTravel(travelId);

        Optional.ofNullable(travelPatchDto.getName())
                .ifPresent(travel::setName);

        Optional.ofNullable(travelPatchDto.getLat())
                .ifPresent(travel::setLat);

        Optional.ofNullable(travelPatchDto.getLng())
                .ifPresent(travel::setLng);

        return toResponseDto(travelRepository.save(travel));
    }

    public void delete(Long travelId) {
        travelRepository.deleteById(travelId);
    }
}
