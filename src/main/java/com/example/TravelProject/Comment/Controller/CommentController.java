package com.example.TravelProject.Comment.Controller;

import com.example.TravelProject.Comment.Dto.CommentPatchDto;
import com.example.TravelProject.Comment.Dto.CommentPostDto;
import com.example.TravelProject.Comment.Dto.CommentResponseDto;
import com.example.TravelProject.Comment.Service.CommentService;
import com.example.TravelProject.Dtos.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;

    @PostMapping
    private ResponseEntity post(@RequestBody CommentPostDto commentPostDto){

        CommentResponseDto commentResponseDto = commentService.post(commentPostDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    private ResponseEntity get(@PathVariable("commentId") Long commentId){

        CommentResponseDto commentResponseDto = commentService.get(commentId);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity gets(Pageable pageable){

        PageResponseDto pageResponseDto = commentService.gets(pageable);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    private ResponseEntity patch(@RequestBody CommentPatchDto commentPatchDto
            , @PathVariable("commentId") Long commentId){

       CommentResponseDto commentResponseDto = commentService.patch(commentPatchDto, commentId);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    private ResponseEntity delete(@PathVariable("commentId") Long commentId){
        commentService.delete(commentId);

        return ResponseEntity.noContent().build();
    }
}
