package com.kh.mango.cs.domain;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class CsUser {
    private int csNo;
    private int csCategory;
    private String csSubject;
    private String csContent;
    private Timestamp csDate;
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
}
