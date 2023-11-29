package com.example.TravelProject.User.Service;

import com.example.TravelProject.Dtos.PageResponseDto;
import com.example.TravelProject.Enum.UserStatus;
import com.example.TravelProject.Exception.BusinessException;
import com.example.TravelProject.Exception.ErrorCode;
import com.example.TravelProject.User.Dto.UserPatchDto;
import com.example.TravelProject.User.Dto.UserPostDto;
import com.example.TravelProject.User.Dto.UserResponseDto;
import com.example.TravelProject.User.Entity.User;
import com.example.TravelProject.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponseDto post(UserPostDto userPostDto) {
        User user = toEntity(userPostDto);
        user.setUserStatus(UserStatus.Acting);
        User save = userRepository.save(user);
        return toResponseDto(save);
    }

    public UserResponseDto get(Long userId) {
        return toResponseDto(verifiedUser(userId));
    }

    public PageResponseDto gets(Pageable pageable) {

        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        return PageResponseDto.of(users,
                new PageImpl(users,
                        userPage.getPageable(),
                        userPage.getTotalElements()));
    }

    public UserResponseDto patch(UserPatchDto userPatchDto, Long userId) {

        User user = verifiedUser(userId);

        Optional.ofNullable(userPatchDto.getName())
                .ifPresent(user::setName);

        Optional.ofNullable(userPatchDto.getUserStatus())
                .ifPresent(user::setUserStatus);

        Optional.ofNullable(userPatchDto.getPassword())
                .ifPresent(user::setPassword);

        Optional.ofNullable(userPatchDto.getEmail())
                .ifPresent(user::setEmail);

        User save = userRepository.save(user);

        return toResponseDto(save);
    }



    private User verifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return user;
    }


    private User toEntity(UserPostDto userPostDto){
        return new User(userPostDto);
    }

    private UserResponseDto toResponseDto(User user){
        return new UserResponseDto(user);
    }



}
