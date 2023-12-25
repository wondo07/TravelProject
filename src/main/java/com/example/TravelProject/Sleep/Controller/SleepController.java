package com.example.TravelProject.Sleep.Controller;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Sleep.Dto.SleepPatchDto;
import com.example.TravelProject.Sleep.Dto.SleepPostDto;
import com.example.TravelProject.Sleep.Dto.SleepResponseDto;
import com.example.TravelProject.Sleep.Service.SleepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sleep")
public class SleepController {

    private final SleepService sleepService;

    @PostMapping
    private ResponseEntity post(@RequestBody SleepPostDto sleepPostDto){
        SleepResponseDto sleepResponseDto = sleepService.post(sleepPostDto);
        return new ResponseEntity<>(sleepResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{sleepId}")
    private ResponseEntity get(@PathVariable("sleepId") Long sleepId){
        SleepResponseDto sleepResponseDto = sleepService.get(sleepId);
        return new ResponseEntity<>(sleepResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){
        PageResponseDto pageResponseDto = sleepService.gets(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{sleepId}")
    private ResponseEntity patch(@PathVariable("sleepId") Long sleepId,
                                 @RequestBody SleepPatchDto sleepPatchDto){
        SleepResponseDto sleepResponseDto = sleepService.patch(sleepId, sleepPatchDto);
        return new ResponseEntity<>(sleepResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{sleepId}")
    private ResponseEntity delete(@PathVariable("sleepId") Long sleepId){
        sleepService.delete(sleepId);
        return ResponseEntity.noContent().build();
    }
}
