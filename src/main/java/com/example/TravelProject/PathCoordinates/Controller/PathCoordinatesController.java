package com.example.TravelProject.PathCoordinates.Controller;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesPatchDto;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesPostDto;
import com.example.TravelProject.PathCoordinates.Dto.PathCoordinatesResponseDto;
import com.example.TravelProject.PathCoordinates.Repository.PathCoordinatesRepository;
import com.example.TravelProject.PathCoordinates.Service.PathCoordinatesService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pathcoordinates")
public class PathCoordinatesController {

    private final PathCoordinatesService pathCoordinatesService;

    @PostMapping
    private ResponseEntity post(@RequestBody PathCoordinatesPostDto pathCoordinatesPostDto){
        PathCoordinatesResponseDto pathCoordinatesResponseDto = pathCoordinatesService.post(pathCoordinatesPostDto);
        return new ResponseEntity<>(pathCoordinatesResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{pathCoordinatesId}")
    private ResponseEntity get(@PathVariable("pathCoordinatesId") Long pathCoordinatesId){
        PathCoordinatesResponseDto pathCoordinatesResponseDto = pathCoordinatesService.get(pathCoordinatesId);
        return new ResponseEntity<>(pathCoordinatesResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){
        PageResponseDto pageResponseDto = pathCoordinatesService.gets(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{pathCoordinatesId}")
    private ResponseEntity patch(@RequestBody PathCoordinatesPatchDto pathCoordinatesPatchDto,
                                 @PathVariable("pathCoordinatesId") Long pathCoordinatesId){
        PathCoordinatesResponseDto pathCoordinatesResponseDto =
                pathCoordinatesService.patch(pathCoordinatesPatchDto, pathCoordinatesId);
        return new ResponseEntity<>(pathCoordinatesResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{pathCoordinatesId}")
    private ResponseEntity delete(@PathVariable("pathCoordinatesId") Long pathCoordinatesId){
        pathCoordinatesService.delete(pathCoordinatesId);
        return ResponseEntity.noContent().build();
    }
}
