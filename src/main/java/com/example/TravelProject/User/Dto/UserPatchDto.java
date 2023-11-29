package com.example.TravelProject.User.Dto;

import com.example.TravelProject.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPatchDto {

    private String name;

    private String email;

    private String password;

    private UserStatus userStatus;
}
