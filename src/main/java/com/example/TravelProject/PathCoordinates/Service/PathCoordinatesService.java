package com.example.TravelProject.PathCoordinates.Service;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesPatchDto;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesPostDto;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesResponseDto;
import com.example.TravelProject.PathCoordinates.Entity.PathCoordinates;
import com.example.TravelProject.PathCoordinates.Repository.PathCoordinatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PathCoordinatesService {

    private final PathCoordinatesRepository pathCoordinatesRepository;

    public PathCoordinatesResponseDto post(PathCoordinatesPostDto pathCoordinatesPostDto) {
        PathCoordinates pathCoordinates = toEntity(pathCoordinatesPostDto);
        PathCoordinates save = pathCoordinatesRepository.save(pathCoordinates);
        return toResponseDto(save);
    }

    public PathCoordinatesResponseDto get(Long pathCoordinatesId) {
        PathCoordinates pathCoordinates = verifiedPathCoordinatesWithQueryDsl(pathCoordinatesId);
        return toResponseDto(pathCoordinates);
    }

    private PathCoordinates verifiedPathCoordinates(Long pathCoordinatesId) {
        Optional<PathCoordinates> optionalPathCoordinates =
                pathCoordinatesRepository.findById(pathCoordinatesId);

        return optionalPathCoordinates.orElseThrow(
                () -> new BusinessException(ErrorCode.PATHCOORDINATES_NOT_FOUND)
        );
    }

    private PathCoordinates verifiedPathCoordinatesWithQueryDsl(Long pathCoordinatesId) {

        PathCoordinates pathCoordinates = pathCoordinatesRepository.findByIdWithQueryDsl(pathCoordinatesId);

        if(pathCoordinates == null){
            throw new BusinessException(ErrorCode.PATHCOORDINATES_NOT_FOUND);
        }

        return pathCoordinates;
    }

    public PageResponseDto gets(Pageable pageable) {
        Page<PathCoordinates> pathCoordinatesPage = pathCoordinatesRepository.findAll(pageable);
        List<PathCoordinates> pathCoordinatesList = pathCoordinatesPage.getContent();
        return PageResponseDto.of(pathCoordinatesList,
                new PageImpl(pathCoordinatesList,
                        pathCoordinatesPage.getPageable(),
                        pathCoordinatesPage.getTotalElements()));
    }

    public PathCoordinatesResponseDto patch(PathCoordinatesPatchDto pathCoordinatesPatchDto, Long pathCoordinatesId) {
        PathCoordinates pathCoordinates = verifiedPathCoordinatesWithQueryDsl(pathCoordinatesId);

        Optional.ofNullable(pathCoordinatesPatchDto.getLat())
                .ifPresent(pathCoordinates::setLat);

        Optional.ofNullable(pathCoordinates.getLng())
                .ifPresent(pathCoordinates::setLng);

        PathCoordinates save = pathCoordinatesRepository.save(pathCoordinates);

        return toResponseDto(save);
    }

    public void delete(Long pathCoordinatesId) {
        pathCoordinatesRepository.deleteById(pathCoordinatesId);
    }

    public PathCoordinates toEntity(PathCoordinatesPostDto pathCoordinatesPostDto){
        return new PathCoordinates(pathCoordinatesPostDto);
    }

    public PathCoordinatesResponseDto toResponseDto(PathCoordinates pathCoordinates){
        return new PathCoordinatesResponseDto(pathCoordinates.getLat(), pathCoordinates.getLng());
    }



}
