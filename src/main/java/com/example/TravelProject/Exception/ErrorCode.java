package com.example.TravelProject.Exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


    USER_NOT_FOUND(404, "USER NOT FOUND"),
    COURSE_NOT_FOUND(404, "COURSE NOT FOUND"),
    COMMENT_NOT_FOUND(404, "COMMENT NOT FOUND"),
    TRAVEL_NOT_FOUND(404, "TRAVEL NOT FOUND"),
    EAT_NOT_FOUND(404, "EAT NOT FOUND"),
    COURSEDATA_NOT_FOUND(404, "COURSEDATA NOT FOUND"),
    SLEEP_NOT_FOUND(404, "SLEEP NOT FOUND"),
    PATHCOORDINATES_NOT_FOUND(404, "PATHCOORDINATES NOT FOUND"),
    USERID_NOT_NULL(404, "USERID NOT NULL");


    private int status;

    private String message;


    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
