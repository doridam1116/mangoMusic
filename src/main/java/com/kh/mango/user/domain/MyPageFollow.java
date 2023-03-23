package com.kh.mango.user.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class MyPageFollow {
    private int followNo;
    private int userNo;
    private String userId;
    private String userName;
    private String userGender;
}
