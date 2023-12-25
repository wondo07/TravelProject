package com.example.TravelProject.Travel.Controller;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Travel.Dto.TravelPatchDto;
import com.example.TravelProject.Travel.Dto.TravelPostDto;
import com.example.TravelProject.Travel.Dto.TravelResponseDto;
import com.example.TravelProject.Travel.Service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @PostMapping
    private ResponseEntity post(@RequestBody TravelPostDto travelPostDto){

        TravelResponseDto travelResponseDto = travelService.post(travelPostDto);

        return new ResponseEntity<>(travelResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{travelId}")
    private ResponseEntity get(@PathVariable("travelId") Long travelId){

        TravelResponseDto travelResponseDto = travelService.get(travelId);

        return new ResponseEntity<>(travelResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){
        PageResponseDto pageResponseDto = travelService.gets(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{travelId}")
    private ResponseEntity patch(@PathVariable("travelId") Long travelId,
                                 @RequestBody TravelPatchDto travelPatchDto){
        TravelResponseDto travelResponseDto = travelService.patch(travelId, travelPatchDto);
        return new ResponseEntity<>(travelResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{travelId}")
    private ResponseEntity delete(@PathVariable Long travelId){
        travelService.delete(travelId);
        return ResponseEntity.noContent().build();
    }
}
