package com.example.TravelProject.Comment.Service;

import com.example.TravelProject.Comment.Dto.CommentPatchDto;
import com.example.TravelProject.Comment.Dto.CommentPostDto;
import com.example.TravelProject.Comment.Dto.CommentResponseDto;
import com.example.TravelProject.Comment.Entity.Comment;
import com.example.TravelProject.Comment.Repository.CommentRepository;
import com.example.TravelProject.Course.Entity.Course;
import com.example.TravelProject.Course.Repository.CourseRepository;
import com.example.TravelProject.Course.Service.CourseService;
import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import com.example.TravelProject.User.Entity.User;
import com.example.TravelProject.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CourseService courseService;


    public CommentResponseDto post(CommentPostDto commentPostDto) {
        Comment comment = toEntity(commentPostDto);
        User user = userService.verifiedUser(commentPostDto.getUserId());
        Course course = courseService.verifiedCourse(commentPostDto.getCourseId());

        user.addComment(comment);
        course.addComment(comment);

        Comment save = commentRepository.save(comment);
        return toResponseDto(save);

    }

    public CommentResponseDto get(Long commentId) {
        Comment comment = verifiedCommentWithQueryDsl(commentId);
        return toResponseDto(comment);
    }

    public PageResponseDto gets(Pageable pageable) {

        Page<Comment> commentPage = commentRepository.findAll(pageable);

        List<Comment> commentList = commentPage.getContent();

        return PageResponseDto.of(commentList,
                new PageImpl(commentList,
                        commentPage.getPageable(),
                        commentPage.getTotalElements()));
    }

    public CommentResponseDto patch(CommentPatchDto commentPatchDto, Long commentId) {
        Comment comment = verifiedCommentWithQueryDsl(commentId);

        Optional.ofNullable(commentPatchDto.getContent())
                .ifPresent(comment::setContent);

        Comment save = commentRepository.save(comment);

        return toResponseDto(save);
    }
    private Comment verifiedComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    public Comment verifiedCommentWithQueryDsl(Long commentId){
        Comment comment = commentRepository.findByIdWithQueryDsl(commentId);
        if(comment == null){
            throw new BusinessException(ErrorCode.COMMENT_NOT_FOUND);
        }
        return comment;
    }

    public Comment toEntity(CommentPostDto commentPostDto){
        return new Comment(commentPostDto);
    }

    public CommentResponseDto toResponseDto(Comment comment){
        return new CommentResponseDto(comment);
    }


    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
