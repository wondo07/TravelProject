package com.example.TravelProject.User.Controller;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.User.Dto.UserPatchDto;
import com.example.TravelProject.User.Dto.UserPostDto;
import com.example.TravelProject.User.Dto.UserResponseDto;
import com.example.TravelProject.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    private ResponseEntity post(@RequestBody UserPostDto userPostDto){

        UserResponseDto userResponseDto = userService.post(userPostDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    private ResponseEntity get(@PathVariable("userId") Long userId){

        UserResponseDto userResponseDto = userService.get(userId);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){

        PageResponseDto pageResponseDto = userService.gets(pageable);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    private ResponseEntity patch(@RequestBody UserPatchDto userPatchDto,
                                 @PathVariable("userId") Long userId){

        UserResponseDto userResponseDto = userService.patch(userPatchDto, userId);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
