package com.example.TravelProject.Eat.Controller;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Eat.Dto.EatPatchDto;
import com.example.TravelProject.Eat.Dto.EatPostDto;
import com.example.TravelProject.Eat.Dto.EatResponseDto;
import com.example.TravelProject.Eat.Service.EatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eat")
public class EatController {

    private final EatService eatService;

    @PostMapping
    private ResponseEntity post(@RequestBody EatPostDto eatPostDto){
        EatResponseDto eatResponseDto = eatService.post(eatPostDto);
        return new ResponseEntity<>(eatResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{eatId}")
    private ResponseEntity get(@PathVariable("eatId") Long eatId){
        EatResponseDto eatResponseDto = eatService.get(eatId);
        return new ResponseEntity<>(eatResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){
        PageResponseDto pageResponseDto = eatService.gets(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{eatId}")
    private ResponseEntity patch(@PathVariable("{eatId}") Long eatId,
                                 @RequestBody EatPatchDto eatPatchDto){

        EatResponseDto eatResponseDto = eatService.patch(eatId,eatPatchDto);

        return new ResponseEntity<>(eatResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{eatId}")
    private ResponseEntity delete(@PathVariable Long eatId){
        eatService.delete(eatId);
        return ResponseEntity.noContent().build();
    }

}
