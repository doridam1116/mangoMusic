package com.kh.mango.user.domain;

import com.kh.mango.point.domain.PointRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

import java.sql.Timestamp;

@Data
public class User {

    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userGender;
    private Integer userType;
    private Timestamp userDate;
    private String userFilename;
    private String userFilepath;
    private String userProfileLetter;
    private int pointVal;

    public User() {
    }

    public User(int userNo, String userFilename, String userFilepath) {
        this.userNo = userNo;
        this.userFilename = userFilename;
        this.userFilepath = userFilepath;
    }

    public User(int userNo, String userId, String userName, String userEmail, String userProfileLetter) {
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userProfileLetter = userProfileLetter;
    }

    private PointRecord pointRecord;

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User(String userName, String userEmail, String userGender) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userGender = userGender;
    }


    public User(String userId, String userPw, String userName, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}

